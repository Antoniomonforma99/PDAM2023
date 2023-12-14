package com.monteroantonio.PDAM23.model.DTOs.direccionEnvio;

import com.fasterxml.jackson.annotation.JsonView;
import com.monteroantonio.PDAM23.model.DireccionEnvio;
import com.monteroantonio.PDAM23.model.view.View;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class DireccionEnvioResponseDTO {

    @JsonView({View.DireccionEnvioView.DireccionGenericView.class})
    private UUID id, idUsuario;

    @JsonView({View.DireccionEnvioView.DireccionGenericView.class})
    private String calle, numero, piso, puerta, poblacion, cp, ciudad, nombreUsuario;


    public static DireccionEnvioResponseDTO of (DireccionEnvio d) {
        return DireccionEnvioResponseDTO.builder()
                .id(d.getId())
                .calle(d.getCalle())
                .numero(d.getNumero())
                .piso(d.getPiso())
                .puerta(d.getPuerta())
                .poblacion(d.getPoblacion())
                .cp(d.getCp())
                .ciudad(d.getCiudad())
                .idUsuario(d.getUsuario().getId())
                .nombreUsuario(d.getUsuario().getName())
                .build();
    }
}
