package pe.edu.vg.service;

import pe.edu.vg.db.AccesoDB;
import pe.edu.vg.dto.DetalleDto;
import pe.edu.vg.dto.VentaDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VentaService {

    public VentaDto registrarVenta(VentaDto venta){
        Connection cn = null;
        int idventa;
        try {
            // Inicio de Tx
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            // Validar empleado
            validarEmpleado(cn,venta.getIdEmpleado());
            // Registrar venta
            idventa = insertaVenta(cn,venta);
            venta.setIdVenta(idventa);
            // Grabarr items
            for(DetalleDto bean: venta.getItems()){
                procesarItem(cn,idventa,bean);
            }
            // Confirmar Tx
            cn.commit();
        } catch(SQLException e){
            try {
                cn.rollback();
            }catch(Exception e1){
            }
            throw new RuntimeException(e.getMessage());
        } catch(Exception e){
            try {
                cn.rollback();
            }catch(Exception e1){
            }
            throw new RuntimeException("Error en el proceso, intentelo nuevamente.");
        } finally {
            try {
                cn.close();
            } catch(Exception e){
            }
        }
        return venta;
    }

    private void procesarItem(Connection cn, int idventa, DetalleDto bean) throws SQLException {
        String sql = "select stock from PRODUCTO where idprod=?";
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1,bean.getIdProducto());
        ResultSet rs = pstm.executeQuery();
        if(!rs.next()){
            throw new SQLException("No existe el producto de codigo " + bean.getIdProducto() + ".");
        }
        int stock = rs.getInt("stock");
        rs.close();
        pstm.close();
        if(bean.getCant() > stock){
            throw new SQLException("No hay stock suficiente.");
        }
        stock -= bean.getCant();
        String sql2 = "update producto set stock=? where idprod=?";
        pstm = cn.prepareStatement(sql2);
        pstm.setInt(1,stock);
        pstm.setInt(2,bean.getIdProducto());
        int filas = pstm.executeUpdate();
        pstm.close();
        if(filas!=1){
            throw new SQLException("Se ha producido un problema, intentelo nuevamente.");
        }
        String sql3 = "insert into detalle(idventa,idprod,cant,precio,subtotal) values(?,?,?,?,?)";
        pstm = cn.prepareStatement(sql3);
        pstm.setInt(1,idventa);
        pstm.setInt(2,bean.getIdProducto());
        pstm.setInt(3,bean.getCant());
        pstm.setDouble(4,bean.getPrecio());
        pstm.setDouble(5,bean.getSubtotal());
        pstm.executeUpdate();
        pstm.close();
    }

    private int insertaVenta(Connection cn, VentaDto venta) throws SQLException {
        String sql = "insert into venta(idemp,cliente,fecha,importe) values(?,?,GETDATE(),?)";
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1,venta.getIdEmpleado());
        pstm.setString(2,venta.getCliente());
        pstm.setDouble(3,venta.getImporte());
        pstm.executeUpdate();
        pstm.close();
        sql = "SELECT IDENT_CURRENT('venta') AS id";
        pstm = cn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        rs.close();
        pstm.close();
        return id;
    }

    private void validarEmpleado(Connection cn, int idEmpleado) throws SQLException {
        String sql = "select count(1) cont from USUARIO where idemp=? and estado=1";
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1,idEmpleado);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int cont = rs.getInt("cont");
        rs.close();
        pstm.close();
        if(cont==0){
            throw new SQLException("Empleado no valido");
        }
    }
}
