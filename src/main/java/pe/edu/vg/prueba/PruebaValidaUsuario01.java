package pe.edu.vg.prueba;

import pe.edu.vg.dto.UsuarioDto;
import pe.edu.vg.service.LogonService;

/**
 * Prueba de usuario que no existe.
 */
public class PruebaValidaUsuario01 {

    public static void main(String[] args) {
        try {
            LogonService service = new LogonService();
            System.out.println("Usuario encontrado");
            UsuarioDto dto1 = service.validarUsuario("Vluyo","admin123");
            System.out.println("----------------------------------------");
            UsuarioDto dto2 = service.validarUsuario("Vluyo","admin123");
            System.out.println(dto1);
            System.out.println(dto2);
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
