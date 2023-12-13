package com.monteroantonio.PDAM23.model.DTOs.categoria;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.producto.ProductoResponseDTO;
import com.monteroantonio.PDAM23.model.view.View;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Builder
@Value
public class CategoriaResponseDTO {

    @JsonView({View.CategoriaView.CategoriaGenericView.class, View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private UUID id;

    @JsonView({View.CategoriaView.CategoriaGenericView.class, View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private String nombre;


    public static CategoriaResponseDTO of (Categoria c) {
        if (c == null)
            return null;

        return CategoriaResponseDTO
                .builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .build();
    }
}
