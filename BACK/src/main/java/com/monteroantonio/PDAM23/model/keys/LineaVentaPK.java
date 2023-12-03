package com.monteroantonio.PDAM23.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor @NoArgsConstructor
@Data
public class LineaVentaPK implements Serializable {

    public UUID ventaId;
    public UUID productoId;
}
