package com.monteroantonio.PDAM23.model;

import com.monteroantonio.PDAM23.model.keys.LineaVentaPK;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class LineaVenta {

    @ManyToOne
    @MapsId("ventaId")
    private Venta venta;

    @ManyToOne
    @MapsId("productoId")
    private Producto producto;

    @EmbeddedId
    private LineaVentaPK pk;

    private Double precioUnidad;

    private int cantidad;

    private Double subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaVenta that)) return false;
        return getCantidad() == that.getCantidad() && Objects.equals(getVenta(), that.getVenta()) && Objects.equals(getProducto(), that.getProducto()) && Objects.equals(getPk(), that.getPk()) && Objects.equals(getPrecioUnidad(), that.getPrecioUnidad()) && Objects.equals(getSubTotal(), that.getSubTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenta(), getProducto(), getPk(), getPrecioUnidad(), getCantidad(), getSubTotal());
    }
}
