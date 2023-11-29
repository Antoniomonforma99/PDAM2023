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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_productos",
            joinColumns = @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "FK_LISTADO_MENU")),
            inverseJoinColumns = @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_LISTADO_PRODUCTOS"))
    )
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @ManyToOne
    private Restaurante restaurante;


    //Metodos helpers

    public void addProducto(Producto p) {
        if (this.getProductos() == null)
            this.setProductos(new ArrayList<>());
        this.getProductos().add(p);

        if (p.getMenus() == null)
            p.setMenus(new ArrayList<>());
        p.getMenus().add(this);
    }

    public void removeProducto(Producto p) {
        p.getMenus().remove(this);
        this.getProductos().remove(p);
    }

    public void addRestaurante(Restaurante res) {
        this.restaurante = res;
        res.getMenus().add(this);
    }

    public void removeRestaurante(Restaurante res) {
        res.getMenus().remove(this);
        this.restaurante = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return Objects.equals(id, menu.id) && Objects.equals(nombre, menu.nombre) && Objects.equals(productos, menu.productos) && Objects.equals(restaurante, menu.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, productos, restaurante);
    }
}
