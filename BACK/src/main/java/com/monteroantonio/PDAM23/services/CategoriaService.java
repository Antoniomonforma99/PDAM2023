package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Categoria add (Categoria c) {
        return repository.save(c);
    }
}
