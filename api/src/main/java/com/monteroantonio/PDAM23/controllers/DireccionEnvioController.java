package com.monteroantonio.PDAM23.controllers;

import com.monteroantonio.PDAM23.model.DTOs.Page.PageDTO;
import com.monteroantonio.PDAM23.model.DTOs.direccionEnvio.DireccionEnvioRequestDTO;
import com.monteroantonio.PDAM23.model.DTOs.direccionEnvio.DireccionEnvioResponseDTO;
import com.monteroantonio.PDAM23.model.DireccionEnvio;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.security.user.service.UserService;
import com.monteroantonio.PDAM23.services.DireccionEnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/direccionEnvio")
public class DireccionEnvioController {

    private final DireccionEnvioService service;
    private final UserService userService;

    @Operation(summary = "Crea una dirección de envío")
    @PostMapping("/new")
    public ResponseEntity<DireccionEnvioResponseDTO> create (
            @AuthenticationPrincipal User loggedUser,
            @RequestBody DireccionEnvioRequestDTO direccionEnvioRequestDTO)
    {
        User user  = userService.findById(loggedUser.getId()).get();

        DireccionEnvioResponseDTO responseDTO = DireccionEnvioResponseDTO.of(service.create(direccionEnvioRequestDTO.toDireccionEnvio(), user));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDTO);
    }


    @Operation(summary = "Obtiene todas las direcciones de envio")
    @PageableAsQueryParam
    @GetMapping("/")
    PageDTO<DireccionEnvioResponseDTO> getAll(@AuthenticationPrincipal User loggedUser,
                                   @PageableDefault(page = 0, size = 10) Pageable pageable) {
        PageDTO<DireccionEnvioResponseDTO> result = new PageDTO<>();

        return result.of(service.findAll(pageable).map(DireccionEnvioResponseDTO::of));
    }

    @Operation(summary = "Elimina una dirección de envío por su id")
    @Parameter(description = "Id de la dirección de envío ", name = "id", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id
            ) {

        if (service.findById(id) == null)
                return ResponseEntity.noContent().build();

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita una dirección de envío por su id")
    @Parameter(description = "Id de la dirección de envío ", name = "id", required = true)
    @PutMapping("/edit/{id}")
    public DireccionEnvioResponseDTO edit(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable UUID id,
            @RequestBody DireccionEnvioRequestDTO direccionEnvioRequestDTO
    ) {
        return DireccionEnvioResponseDTO.of(service.edit(id, direccionEnvioRequestDTO, loggedUser));
    }

    @Operation(summary = "Obtiene una direccion de envío por su id")
    @Parameter(description = "Id de la dirección de envío ", name = "id", required = true)
    @GetMapping("/{id}")
    public DireccionEnvioResponseDTO getById(@PathVariable UUID id) {
        return DireccionEnvioResponseDTO.of(service.findById(id));
    }
}
