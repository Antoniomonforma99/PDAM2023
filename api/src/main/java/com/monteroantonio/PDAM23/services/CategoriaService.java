package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaRequestDTO;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import com.monteroantonio.PDAM23.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final ProductoRepository productoRepository;

    public Categoria findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Categoria> findAll() { return repository.findAll();}

    public Categoria edit (CategoriaRequestDTO categoriaRequestDTO, UUID id) {
        return repository.findById(id).map(c -> {
            c.setNombre(categoriaRequestDTO.getNombre());

            return c;
        }).orElseThrow(EntityNotFoundException::new);
    }

    public Categoria add (Categoria c) {
        return repository.save(c);
    }

    @Transactional
    public void deleteById(UUID id) {

        repository.findById(id).map(c -> {
            c.getProductos().forEach(p -> {
                p.setCategoria(null);

                productoRepository.save(p);
            });

            return c;
        });

        repository.deleteById(id);
    }

}
