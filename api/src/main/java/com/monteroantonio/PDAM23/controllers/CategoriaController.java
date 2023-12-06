package com.monteroantonio.PDAM23.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaRequestDTO;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaResponseDTO;
import com.monteroantonio.PDAM23.model.view.View;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @Operation(summary = "Obtiene todas las categorias")
    @JsonView({View.CategoriaView.CategoriaGenericView.class})
    @GetMapping("/{id}")
    public List<CategoriaResponseDTO> findAll() {
        return service.findAll().stream().map(CategoriaResponseDTO::of).toList();
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

}
