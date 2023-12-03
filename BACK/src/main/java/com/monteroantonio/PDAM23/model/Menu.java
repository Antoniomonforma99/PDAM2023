package com.monteroantonio.PDAM23.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Menu {

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

    @ManyToMany(mappedBy = "menus", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return Objects.equals(getId(), menu.getId()) && Objects.equals(getNombre(), menu.getNombre()) && Objects.equals(getProductos(), menu.getProductos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getProductos());
    }
}
