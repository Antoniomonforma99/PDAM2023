package com.monteroantonio.PDAM23.model.DTOs.direccionEnvio;

import com.monteroantonio.PDAM23.model.DireccionEnvio;
import com.monteroantonio.PDAM23.security.user.User;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Value
public class DireccionEnvioRequestDTO {

    @NotBlank
    private String calle, numero, piso, puerta, poblacion, cp, ciudad;

    //private User idUser;

    public DireccionEnvio toDireccionEnvio() {
        return DireccionEnvio.builder()
                .calle(calle)
                .numero(numero)
                .piso(piso)
                .puerta(puerta)
                .poblacion(poblacion)
                .ciudad(ciudad)
                .cp(cp)
                .build();
    }


}
