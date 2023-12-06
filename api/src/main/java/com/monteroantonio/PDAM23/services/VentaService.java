package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Venta;
import com.monteroantonio.PDAM23.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    private final ProductoService productoService;

    public Venta findDetails(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
