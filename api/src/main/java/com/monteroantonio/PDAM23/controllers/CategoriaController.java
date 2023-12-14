package com.monteroantonio.PDAM23.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaRequestDTO;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaResponseDTO;
import com.monteroantonio.PDAM23.model.view.View;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @Operation(summary = "Obtiene todas las categorias")
    @JsonView({View.CategoriaView.CategoriaGenericView.class})
    @GetMapping("/")
    public List<CategoriaResponseDTO> findAll() {
        return service.findAll().stream().map(CategoriaResponseDTO::of).toList();
    }

    @Operation(summary = "Crea una nueva categoría")
    @JsonView({View.CategoriaView.CategoriaGenericView.class})
    @PostMapping("/new")
    public CategoriaResponseDTO create(
            @AuthenticationPrincipal User loggedUser,
            @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO
    ) {
        return CategoriaResponseDTO.of(service.add(categoriaRequestDTO.toCategoria()));
    }

    @Operation(summary = "Edita una categoria por su id")
    @JsonView({View.CategoriaView.CategoriaGenericView.class})
    @PutMapping("/{id}")
    public CategoriaResponseDTO edit(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id,
            @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO
            ) {
        return CategoriaResponseDTO.of(service.edit(categoriaRequestDTO, id));
    }

    @Operation(summary = "Obitene una categoria por su id")
    @GetMapping("/{id}")
    public ResponseEntity<List<Categoria>> getById(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id
    ) {

        List<Categoria> categorias = service.findAll();

        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Borra una categoria por su id")
    @Parameter(description = "Id de la categoria", name = "id", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id
    ){
        if (service.findById(id) == null)
            return ResponseEntity.noContent().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
