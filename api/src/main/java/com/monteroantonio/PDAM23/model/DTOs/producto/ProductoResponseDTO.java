package com.monteroantonio.PDAM23.model.DTOs.producto;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaResponseDTO;
import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.model.view.View;
import lombok.Builder;
import lombok.Value;
import org.hibernate.LazyInitializationException;

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

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private Double valoracionMedia;

    @JsonView({View.ProductoView.ProductoDetailedView.class})
    private List<String> ingredientes;

    @JsonView({View.ProductoView.ProductoGenericView.class, View.ProductoView.ProductoDetailedView.class})
    private CategoriaResponseDTO categoria;


    public static ProductoResponseDTO of (Producto p) {
        CategoriaResponseDTO categoria = null;

        try {
            categoria = CategoriaResponseDTO.of(p.getCategoria());
        } catch (LazyInitializationException e) {
            categoria = null;
        }

        return ProductoResponseDTO
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .imgUrl(p.getImgUrl())
                .precio(p.getPrecio())
                .valoracionMedia(p.getValoracionMedia())
                .ingredientes(p.getIngredientes())
                .categoria(categoria)
                .build();
    }
}
