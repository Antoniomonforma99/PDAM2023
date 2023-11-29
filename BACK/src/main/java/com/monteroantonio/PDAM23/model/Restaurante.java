package com.monteroantonio.PDAM23.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Restaurante {

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

    private String nombre;
    private String direccion;
    private String imgUrl;
    private LocalTime horaApertura, horaCierre;

    @OneToMany(mappedBy = "restaurante")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurante")
    @Builder.Default
    private List<Menu> menus = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurante that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(horaApertura, that.horaApertura) && Objects.equals(horaCierre, that.horaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, imgUrl, horaApertura, horaCierre);
    }


}
