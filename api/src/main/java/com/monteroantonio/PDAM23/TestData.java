package com.monteroantonio.PDAM23;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.Menu;
import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.model.Restaurante;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import com.monteroantonio.PDAM23.repository.MenuRepository;
import com.monteroantonio.PDAM23.repository.ProductoRepository;
import com.monteroantonio.PDAM23.repository.RestauranteRepository;
import com.monteroantonio.PDAM23.security.user.Roles;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MenuRepository menuRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void initData(){

        Restaurante r1 = Restaurante.builder()
                .nombre("Masakali 1")
                .direccion("C/ calle sin numero")
                .imgUrl("imagen")
                .horaApertura(LocalTime.NOON)
                .horaCierre(LocalTime.MIDNIGHT)
                .build();

        restauranteRepository.save(r1);


        Categoria c1 = Categoria.builder()
                .nombre("Hamburgesas")
                .productos(new ArrayList<>())
                .build();

        Categoria c2 = Categoria.builder()
                .nombre("Pizzas")
                .productos(new ArrayList<>())
                .build();

        Categoria c3 = Categoria.builder()
                .nombre("Bebidas")
                .productos(new ArrayList<>())
                .build();

        Categoria c4 = Categoria.builder()
                .nombre("Postres")
                .productos(new ArrayList<>())
                .build();


        categoriaRepository.saveAll(List.of(c1, c2, c3, c4));

        //Burguers

        Producto p1 = Producto.builder()
                .nombre("Hamburgeson")
                .categoria(c1)
                .descripcion("El hamburgueson")
                .imgUrl("url")
                .valoracionMedia(6.5)
                .precio(9.95)
                .ingredientes(List.of("Pan", "Carne", "Huevo", "Lechuga", "Queso"))
                .build();

        Producto p2 = Producto.builder()
                .nombre("Hamburgeson2")
                .descripcion("El hamburgueson2")
                .imgUrl("url2")
                .precio(9.95)
                .valoracionMedia(9.5)
                .ingredientes(List.of("Pan2", "Carne2", "Huev2o", "Lechug2a", "Que2so"))
                .build();

        Producto p3 = Producto.builder()
                .nombre("Hamburguesa Clásica")
                .categoria(c1)
                .descripcion("La hamburguesa clásica con ingredientes frescos.")
                .imgUrl("url_clasica")
                .precio(8.50)
                .valoracionMedia(8.5)
                .ingredientes(List.of("Pan", "Carne", "Lechuga", "Tomate", "Cebolla", "Queso"))
                .build();

        Producto p4 = Producto.builder()
                .nombre("Hamburguesa Vegetariana")
                .categoria(c1)
                .descripcion("Una opción vegetariana llena de sabor.")
                .imgUrl("url_vegetariana")
                .precio(7.95)
                .valoracionMedia(6.5)
                .ingredientes(List.of("Pan integral", "Falafel", "Lechuga", "Tomate", "Pepino", "Queso vegano"))
                .build();

        Producto p5 = Producto.builder()
                .nombre("Hamburguesa BBQ")
                .categoria(c1)
                .descripcion("Una hamburguesa con deliciosa salsa BBQ.")
                .imgUrl("url_bbq")
                .precio(10.25)
                .valoracionMedia(6.5)
                .ingredientes(List.of("Pan de cebolla", "Carne", "Bacon", "Cebolla caramelizada", "Queso cheddar", "Salsa BBQ"))
                .build();

        c1.getProductos().addAll(List.of(p1, p2, p3, p4, p5));

        p1.addCategoria(c1);
        p2.addCategoria(c1);
        p3.addCategoria(c1);
        p4.addCategoria(c1);
        p5.addCategoria(c1);

        productoRepository.saveAll(List.of(p1, p2, p3, p4, p5));

        //PIZZAS
        Producto p6 = Producto.builder()
                .nombre("Pizza BBQ")
                .categoria(c2)
                .descripcion("Una pizza con deliciosa salsa BBQ y sabrosos ingredientes.")
                .imgUrl("url_bbq_pizza")
                .precio(12.75)
                .valoracionMedia(7.2)
                .ingredientes(List.of("Masa especial", "Salsa BBQ", "Pollo a la barbacoa", "Bacon", "Cebolla caramelizada", "Queso ahumado"))
                .build();

        Producto p7 = Producto.builder()
                .nombre("Pizza Cuatro Quesos")
                .categoria(c2)
                .descripcion("Una combinación exquisita de cuatro quesos diferentes.")
                .imgUrl("url_cuatro_quesos")
                .precio(11.50)
                .valoracionMedia(8.0)
                .ingredientes(List.of("Masa tradicional", "Mozzarella", "Gorgonzola", "Queso de cabra", "Parmesano"))
                .build();

        Producto p8 = Producto.builder()
                .nombre("Pizza Mediterránea")
                .categoria(c2)
                .descripcion("Una pizza con ingredientes frescos típicos del Mediterráneo.")
                .imgUrl("url_mediterranea")
                .precio(13.25)
                .valoracionMedia(8.5)
                .ingredientes(List.of("Masa fina", "Salsa de tomate", "Aceitunas", "Tomate fresco", "Albahaca", "Queso mozzarella"))
                .build();

        Producto p9 = Producto.builder()
                .nombre("Pizza Picante Mexicana")
                .categoria(c2)
                .descripcion("Una pizza con toque picante y sabores mexicanos.")
                .imgUrl("url_picante_mexicana")
                .precio(12.95)
                .valoracionMedia(7.8)
                .ingredientes(List.of("Masa de maíz", "Chorizo picante", "Chiles jalapeños", "Cebolla morada", "Salsa de tomate", "Queso cheddar"))
                .build();

        c2.getProductos().addAll(List.of(p6, p7, p8, p9));

        p6.addCategoria(c2);
        p7.addCategoria(c2);
        p8.addCategoria(c2);
        p9.addCategoria(c2);

        productoRepository.saveAll(List.of(p6, p7, p8, p9));

        //BEBIDAS

        Producto p10 = Producto.builder()
                .nombre("Refresco de Naranja")
                .categoria(c3)
                .descripcion("Refresco con un toque cítrico a naranja.")
                .imgUrl("url_naranja")
                .precio(2.75)
                .valoracionMedia(7.5)
                .build();

        Producto p11 = Producto.builder()
                .nombre("Refresco de Limón")
                .categoria(c3)
                .descripcion("Bebida refrescante con sabor a limón.")
                .imgUrl("url_limon")
                .precio(2.50)
                .valoracionMedia(8.2)
                .build();

        Producto p12 = Producto.builder()
                .nombre("Smoothie de Frutas")
                .categoria(c3)
                .descripcion("Bebida natural con una mezcla de frutas frescas.")
                .imgUrl("url_smoothie")
                .precio(4.00)
                .valoracionMedia(9.0)
                .ingredientes(List.of("Fresas", "Plátano", "Mango", "Yogur", "Miel"))
                .build();

        Producto p13 = Producto.builder()
                .nombre("Batido de Chocolate")
                .categoria(c3)
                .descripcion("Un clásico batido cremoso de chocolate.")
                .imgUrl("url_batido_chocolate")
                .precio(3.75)
                .valoracionMedia(8.8)
                .ingredientes(List.of("Leche", "Chocolate", "Helado de vainilla", "Crema batida"))
                .build();

        c3.getProductos().addAll(List.of(p10, p11, p12, p13));

        p10.addCategoria(c3);
        p11.addCategoria(c3);
        p12.addCategoria(c3);
        p13.addCategoria(c3);

        //productoRepository.saveAll(List.of(p10, p11, p12, p13));

        //POSTRES

        Producto p14 = Producto.builder()
                .nombre("Pastel de Chocolate")
                .categoria(c4)
                .descripcion("Un delicioso pastel de chocolate con cobertura suave.")
                .imgUrl("url_pastel_chocolate")
                .precio(6.50)
                .valoracionMedia(9.5)
                .ingredientes(List.of("Harina", "Chocolate", "Azúcar", "Huevos", "Mantequilla", "Crema de chocolate"))
                .build();

        Producto p15 = Producto.builder()
                .nombre("Helado de Vainilla")
                .categoria(c4)
                .descripcion("Helado suave y cremoso con sabor a vainilla.")
                .imgUrl("url_helado_vainilla")
                .precio(4.25)
                .valoracionMedia(8.9)
                .ingredientes(List.of("Leche", "Nata", "Vainilla", "Azúcar"))
                .build();

        Producto p16 = Producto.builder()
                .nombre("Tarta de Fresas")
                .categoria(c4)
                .descripcion("Tarta fresca con fresas y crema.")
                .imgUrl("url_tarta_fresas")
                .precio(7.75)
                .valoracionMedia(9.2)
                .ingredientes(List.of("Fresas frescas", "Crema pastelera", "Bizcocho", "Gelatina de fresa"))
                .build();

        Producto p17 = Producto.builder()
                .nombre("Brownie con Nueces")
                .categoria(c4)
                .descripcion("Brownie denso y delicioso con trozos de nueces.")
                .imgUrl("url_brownie_nueces")
                .precio(5.00)
                .valoracionMedia(9.0)
                .ingredientes(List.of("Chocolate", "Harina", "Azúcar", "Huevos", "Nueces", "Mantequilla"))
                .build();

        c4.getProductos().addAll(List.of(p14, p15, p16, p17));

        p14.addCategoria(c4);
        p15.addCategoria(c4);
        p16.addCategoria(c4);
        p17.addCategoria(c4);

        productoRepository.saveAll(List.of(p14, p15, p16, p17));









        Menu m1 = Menu.builder()
                .nombre("Supermenu")
                .productos(List.of(p1))
                .build();

        menuRepository.save(m1);

        p1.getMenus().add(m1);
        p2.getMenus().add(m1);

        productoRepository.save(p1);
        productoRepository.save(p2);



    }
}
