package pe.edu.vg.service;


import java.sql.*;
import pe.edu.vg.db.AccesoDB;
import pe.edu.vg.dto.UsuarioDto;

import java.net.URLConnection;

public class LogonService {

    public UsuarioDto validarUsuario(String usuario, String clave){
        UsuarioDto dto = new UsuarioDto();
        String sql = """
                select e.idemp, e.apellido, e.nombre 
                from USUARIO u 
                join EMPLEADO e on u.idemp = e.idemp 
                where USUARIO=? 
                and CLAVE=CAST(HashBytes('SHA1',?) AS VARBINARY) 
                and ESTADO=1
                """;
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs = null;
        try {
            cn = AccesoDB.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setString(1,usuario);
            pstm.setBytes(2,clave.getBytes());
            rs = pstm.executeQuery();
            if(!rs.next()){
                throw new SQLException("Datos incorrectos");
            }
            dto.setId(rs.getInt("idemp"));
            dto.setNombre(rs.getString("nombre"));
            dto.setApellido(rs.getString("apellido"));
            dto.setUsuario(usuario);
            rs.close();
            pstm.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch(Exception e){
            throw new RuntimeException("Error en el proceso.");
        }
        return dto;
    }
}
