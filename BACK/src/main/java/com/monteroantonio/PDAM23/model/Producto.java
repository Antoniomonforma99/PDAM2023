package com.monteroantonio.PDAM23.model;

import com.monteroantonio.PDAM23.model.utils.StringListConverter;
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
public class Producto {

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
    private String descripcion;
    private String imgUrl;
    private Double precio;

    @Builder.Default
    @Convert(converter = StringListConverter.class)
    private List<String> ingredientes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria", foreignKey = @ForeignKey(name = "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "restaurante", foreignKey = @ForeignKey(name = "FK_PRODUCTO_RESTAURANTE"))
    private Restaurante restaurante ;

    @ManyToMany(mappedBy = "productos",  fetch = FetchType.EAGER)
    @Builder.Default
    private List<Menu> menus = new ArrayList<>();


    //Métodos helpers para gestionar las asociaciones bidireccionales

    public void addCategoria (Categoria c) {
        this.categoria = c;
        c.getProductos().add(this);
    }

    public void removeCategoria (Categoria c) {
        c.getProductos().remove(this);
        this.categoria = null;
    }
    public void addRestaurante(Restaurante res) {
        this.restaurante = res;
        res.getProductos().add(this);
    }

    public void removeRestaurante(Restaurante res) {
        res.getProductos().remove(this);
        this.restaurante = null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(imgUrl, producto.imgUrl) && Objects.equals(precio, producto.precio) && Objects.equals(ingredientes, producto.ingredientes) && Objects.equals(restaurante, producto.restaurante) && Objects.equals(menus, producto.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, imgUrl, precio, ingredientes, restaurante, menus);
    }


}
