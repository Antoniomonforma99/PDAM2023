package com.monteroantonio.PDAM23.model.DTOs.categoria;

import com.monteroantonio.PDAM23.model.Categoria;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
@Value
public class CategoriaRequestDTO {

    private UUID id;

    @NotBlank
    private String nombre;

    public Categoria toCategoria(){
        return Categoria.builder()
                .id(id)
                .nombre(nombre)
                .build();
    }

}
