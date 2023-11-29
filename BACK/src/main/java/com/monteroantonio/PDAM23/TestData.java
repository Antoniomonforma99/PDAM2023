package com.monteroantonio.PDAM23;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.Menu;
import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.model.Restaurante;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import com.monteroantonio.PDAM23.repository.MenuRepository;
import com.monteroantonio.PDAM23.repository.ProductoRepository;
import com.monteroantonio.PDAM23.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MenuRepository menuRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @PostConstruct
    public void initData(){

        Restaurante r1 = Restaurante.builder()
                .nombre("Masakali 1")
                .direccion("C/ calle sin numero")
                .imgUrl("imagen")
                .productos(Collections.emptyList())
                .horaApertura(LocalTime.NOON)
                .horaCierre(LocalTime.MIDNIGHT)
                .build();

        Restaurante r2 = Restaurante.builder()
                .nombre("Masakali 2")
                .direccion("C/ calle con numero")
                .imgUrl("imagen2")
                .productos(Collections.emptyList())
                .horaApertura(LocalTime.NOON)
                .horaCierre(LocalTime.MIDNIGHT)
                .build();



        Categoria c1 = Categoria.builder()
                .nombre("Hamburgesas")
                .build();


        categoriaRepository.save(c1);

        Producto p1 = Producto.builder()
                .nombre("Hamburgeson")
                .categoria(c1)
                .descripcion("El hamburgueson")
                .imgUrl("url")
                .precio(9.95)
                .ingredientes(List.of("Pan", "Carne", "Huevo", "Lechuga", "Queso"))
                .build();

        Producto p2 = Producto.builder()
                .nombre("Hamburgeson2")
                .categoria(c1)
                .descripcion("El hamburgueson2")
                .imgUrl("url2")
                .precio(9.95)
                .ingredientes(List.of("Pan2", "Carne2", "Huev2o", "Lechug2a", "Que2so"))
                .build();



        Menu m1 = Menu.builder()
                .nombre("Supermenu")
                .productos(List.of(p1, p2))
                .build();



        restauranteRepository.save(r1);
        restauranteRepository.save(r2);

        productoRepository.save(p1);
        productoRepository.save(p2);



        menuRepository.save(m1);

        m1.addRestaurante(r1);


        /*p1.addRestaurante(r1);
        p2.addRestaurante(r1);

         */






    }
}
