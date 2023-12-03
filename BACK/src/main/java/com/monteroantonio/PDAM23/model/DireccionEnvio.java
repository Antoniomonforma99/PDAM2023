package com.monteroantonio.PDAM23.model;

import com.monteroantonio.PDAM23.security.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class DireccionEnvio {

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

    private String calle, numero, piso, puerta, poblacion, cp, ciudad;

    @ManyToOne
    private User usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DireccionEnvio that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCalle(), that.getCalle()) && Objects.equals(getNumero(), that.getNumero()) && Objects.equals(getPiso(), that.getPiso()) && Objects.equals(getPuerta(), that.getPuerta()) && Objects.equals(getPoblacion(), that.getPoblacion()) && Objects.equals(getCp(), that.getCp()) && Objects.equals(getCiudad(), that.getCiudad()) && Objects.equals(getUsuario(), that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCalle(), getNumero(), getPiso(), getPuerta(), getPoblacion(), getCp(), getCiudad(), getUsuario());
    }
}
