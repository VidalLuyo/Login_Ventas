package pe.edu.vg.prueba;

import pe.edu.vg.db.AccesoDB;

import java.sql.Connection;

public class PruebaConection {

    public static void main(String[] args) {
        try {
            Connection cn = AccesoDB.getConnection();
            System.out.println("Conexion ok!");
            cn.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
