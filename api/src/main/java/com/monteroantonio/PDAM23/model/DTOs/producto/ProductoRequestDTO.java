package com.monteroantonio.PDAM23.model.DTOs.producto;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaRequestDTO;
import com.monteroantonio.PDAM23.model.Menu;
import com.monteroantonio.PDAM23.model.Producto;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@Value
public class ProductoRequestDTO {

    @NotBlank
    private String nombre;

    @Positive
    private Double precio;

    @NotBlank
    private String descripcion;

    @NotEmpty
    private List<String> ingredientes;

    private List<Menu> menus;

    private CategoriaRequestDTO categoria;

    public Producto toProducto() {
        Categoria categoriaSeleccionada = categoria == null ? null : categoria.toCategoria();

        return Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .descripcion(descripcion)
                .ingredientes(ingredientes)
                .menus(menus)
                .categoria(categoriaSeleccionada)
                .build();
    }


}
