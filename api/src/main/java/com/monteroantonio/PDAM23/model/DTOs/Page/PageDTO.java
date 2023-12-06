package com.monteroantonio.PDAM23.model.DTOs.Page;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.view.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonView(
        {View.ProductoView.ProductoGenericView.class,
        View.ProductoView.ProductoDetailedView.class}
)
public class PageDTO<T> {

    private List<T> contenido;
    private Integer numResultados;
    private Integer numPaginas;
    private Integer paginaActual;

    public PageDTO<T> of (Page page) {
        this.contenido = page.getContent();
        this.numResultados = page.getNumberOfElements();
        this.numPaginas = page.getTotalPages();
        this.paginaActual = page.getNumber();

        return this;
    }
}
