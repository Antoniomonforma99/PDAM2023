package com.monteroantonio.PDAM23.model;

import com.monteroantonio.PDAM23.security.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Venta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    @OneToMany(mappedBy = "venta")
    private List<LineaVenta> lineasVentas;

    private LocalDateTime fecha;

    @ManyToOne
    private User usuario;

    @ManyToOne
    private DireccionEnvio direccionEnvio;

    private Double totalPedido;

    private Double precioEnvio;

    private Double totalFinal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta venta)) return false;
        return Objects.equals(getId(), venta.getId()) && Objects.equals(getLineasVentas(), venta.getLineasVentas()) && Objects.equals(getFecha(), venta.getFecha()) && Objects.equals(getUsuario(), venta.getUsuario()) && Objects.equals(getDireccionEnvio(), venta.getDireccionEnvio()) && Objects.equals(getTotalPedido(), venta.getTotalPedido()) && Objects.equals(getPrecioEnvio(), venta.getPrecioEnvio()) && Objects.equals(getTotalFinal(), venta.getTotalFinal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLineasVentas(), getFecha(), getUsuario(), getDireccionEnvio(), getTotalPedido(), getPrecioEnvio(), getTotalFinal());
    }
}
