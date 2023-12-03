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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "producto_id",
                    foreignKey = @ForeignKey(name = "FK_TIENE_PRODUCTO")),
                inverseJoinColumns = @JoinColumn(name = "menu_id",
                    foreignKey = @ForeignKey(name = "FK_TIENE_MENU")),
            name = "menu_productos"
    )
    @Builder.Default
    private List<Menu> menus = new ArrayList<>();


    //Métodos helpers para gestionar las asociaciones bidireccionales

    public void addMenu(Menu m){
        if (this.getMenus() == null)
            this.setMenus(new ArrayList<>());
        this.getMenus().add(m);

        if (m.getProductos() == null)
            m.setProductos(new ArrayList<>());
        m.getProductos().add(this);
    }

    public void removeMenu(Menu m) {
        m.getProductos().remove(this);
        this.getMenus().remove(m);
    }

    public void addCategoria (Categoria c) {
        this.categoria = c;
        c.getProductos().add(this);
    }

    public void removeCategoria (Categoria c) {
        c.getProductos().remove(this);
        this.categoria = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(getId(), producto.getId()) && Objects.equals(getNombre(), producto.getNombre()) && Objects.equals(getDescripcion(), producto.getDescripcion()) && Objects.equals(getImgUrl(), producto.getImgUrl()) && Objects.equals(getPrecio(), producto.getPrecio()) && Objects.equals(getIngredientes(), producto.getIngredientes()) && Objects.equals(getCategoria(), producto.getCategoria()) && Objects.equals(getMenus(), producto.getMenus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getDescripcion(), getImgUrl(), getPrecio(), getIngredientes(), getCategoria(), getMenus());
    }
}
