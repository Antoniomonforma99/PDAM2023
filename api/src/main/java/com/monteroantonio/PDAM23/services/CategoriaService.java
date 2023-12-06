package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.categoria.CategoriaRequestDTO;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

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
}
