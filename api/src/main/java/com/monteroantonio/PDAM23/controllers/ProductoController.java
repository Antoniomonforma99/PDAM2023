package com.monteroantonio.PDAM23.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.Page.PageDTO;
import com.monteroantonio.PDAM23.model.DTOs.producto.ProductoRequestDTO;
import com.monteroantonio.PDAM23.model.DTOs.producto.ProductoResponseDTO;
import com.monteroantonio.PDAM23.model.view.View;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.services.CategoriaService;
import com.monteroantonio.PDAM23.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;
    private final CategoriaService categoriaService;

    @Operation(summary = "Obtiene todos los productos")
    @PageableAsQueryParam
    @JsonView(View.ProductoView.ProductoGenericView.class)
    @GetMapping("/")
    PageDTO<ProductoResponseDTO> getAll(@PageableDefault(page = 0, size = 10)Pageable pageable) {

        PageDTO<ProductoResponseDTO> result = new PageDTO<>();

        return result.of(service.findAll(pageable).map(ProductoResponseDTO::of));
    }

    @Operation(summary = "Obtiene los detalles de un producto")
    @Parameter(description = "Id del producto", name = "id", required = true)
    @JsonView(View.ProductoView.ProductoDetailedView.class)
    @GetMapping("/{id}")
    public ProductoResponseDTO getById(@PathVariable UUID id) {
        return ProductoResponseDTO.of(service.findById(id));
    }

    @Operation(summary = "Borra un producto por su id")
    @Parameter(description = "Id del producto", name = "id", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id
            ){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crea un producto")
    @JsonView(View.ProductoView.ProductoDetailedView.class)
    @PostMapping("/new")
    public ResponseEntity<ProductoResponseDTO> create (
            @AuthenticationPrincipal User loggedUser,
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("body") ProductoRequestDTO productoRequestDTO
            ){

        Categoria categoria = categoriaService.findById(productoRequestDTO.getIdCategoria());


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ProductoResponseDTO.of(service.add(productoRequestDTO.toProducto(), categoria, loggedUser, file))
                );
    }

    @Operation(summary = "Edita un producto")
    @Parameter(description = "Id del producto a editar", name = "id", required = true)
    @PutMapping("/edit/{id}")
    public ProductoResponseDTO edit(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID uuid,
            @Valid @RequestBody ProductoRequestDTO productoRequestDTO
    ) {
        Categoria categoria = categoriaService.findById(productoRequestDTO.getIdCategoria());
        return ProductoResponseDTO.of(service.edit(uuid, productoRequestDTO, categoria, loggedUser));
    }

    @Operation(summary = "Añade una categoria a un producto")
    @Parameter(description = "Id del prodcuto", name = "id", required = true)
    @Parameter(description = "Id de la categoria", name = "idCategoria", required = true)
    @PostMapping("/{id}/addCategoria/{idCategoria}")
    public ProductoResponseDTO addCategoria(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id,
            @PathVariable UUID idCategoria
    ) {
        return ProductoResponseDTO.of(service.addCategoria(id, idCategoria));
    }

    @Operation(summary = "Elimina una categoria de un producto")
    @Parameter(description = "Id del prodcuto", name = "id", required = true)
    @Parameter(description = "Id de la categoria", name = "idCategoria", required = true)
    @DeleteMapping("/{id}/deleteCategoria/{idCategoria}")
    public ProductoResponseDTO deleteCategoria(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id,
            @PathVariable UUID idCategoria
    ) {
        return ProductoResponseDTO.of(service.deleteCategoria(id, idCategoria));
    }




}
