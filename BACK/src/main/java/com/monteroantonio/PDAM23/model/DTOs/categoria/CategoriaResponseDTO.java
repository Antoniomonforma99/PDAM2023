package com.monteroantonio.PDAM23.model.DTOs.categoria;

import com.monteroantonio.PDAM23.model.Categoria;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class CategoriaResponseDTO {

    private UUID id;

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
