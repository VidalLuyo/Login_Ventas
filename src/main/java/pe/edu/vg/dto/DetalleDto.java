package pe.edu.vg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDto {

    private int idProducto;
    private String nombre;
    private int cant;
    private double precio;
    private double subtotal;


}
