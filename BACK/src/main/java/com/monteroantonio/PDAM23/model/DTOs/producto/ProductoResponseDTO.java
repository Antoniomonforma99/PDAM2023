package com.monteroantonio.PDAM23.model.DTOs.producto;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaResponseDTO;
import com.monteroantonio.PDAM23.model.view.View;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Builder
@Value
public class ProductoResponseDTO {

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private UUID id;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private String nombre;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private String descripcion;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private String imgUrl;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private Double precio;

    @JsonView({View.ProductoView.ProductoDetailedView.class})
    private List<String> ingredientes;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private CategoriaResponseDTO categoria;
}
