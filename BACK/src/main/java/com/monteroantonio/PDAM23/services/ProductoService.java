package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public Producto findById(UUID id) {
        return repository.findFirstById(id).orElseThrow(EntityNotFoundException::new);
    }
}
