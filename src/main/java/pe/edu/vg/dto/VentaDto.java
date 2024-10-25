package pe.edu.vg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class VentaDto {

    private int idVenta;
    private int idEmpleado;
    private String cliente;
    private String fecha;
    private double importe;
    private List<DetalleDto> items;

    public VentaDto(){
        items = new ArrayList<>();
    }

}
