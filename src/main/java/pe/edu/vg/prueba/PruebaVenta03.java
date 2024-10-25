package pe.edu.vg.prueba;

import pe.edu.vg.dto.DetalleDto;
import pe.edu.vg.dto.VentaDto;
import pe.edu.vg.service.VentaService;

/**
 * Registra la venta completa con items.
 */
public class PruebaVenta03 {

    public static void main(String[] args) {
        try {
            // Datos
            VentaDto venta = new VentaDto();
            venta.setIdEmpleado(1005);
            venta.setCliente("Vidal Luyo");
            venta.setImporte(700);
            venta.getItems().add(new DetalleDto(2,"YOGURT",2,150,300));
            venta.getItems().add(new DetalleDto(4,"CEREAL",2,95,190));
            venta.getItems().add(new DetalleDto(6,"FRUTAS",1,140,140));
            // Proceso
            VentaService service = new VentaService();
            venta = service.registrarVenta(venta);
            // Reporte
            System.out.println("Venta Ok.");
            System.out.println(venta);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
}
