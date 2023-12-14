package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.DTOs.Page.PageDTO;
import com.monteroantonio.PDAM23.model.DTOs.direccionEnvio.DireccionEnvioRequestDTO;
import com.monteroantonio.PDAM23.model.DireccionEnvio;
import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.repository.DireccionEnvioRepository;
import com.monteroantonio.PDAM23.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DireccionEnvioService {

    private final DireccionEnvioRepository repository;

    public DireccionEnvio create(DireccionEnvio direccionEnvio, User user){
        direccionEnvio.setUsuario(user);
        return repository.save(direccionEnvio);
    }

    public Page<DireccionEnvio> findAll(Pageable pageable) {
        Page<DireccionEnvio> result = repository.findAll(pageable);

        if (result.getContent().isEmpty())
            throw new EntityNotFoundException("No se han encontrado direcciones de envío");

        return repository.findAll(pageable);
    }

    public DireccionEnvio findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public DireccionEnvio edit (UUID id, DireccionEnvioRequestDTO direccionEnvioRequestDTO, User loggedUser) {
        return repository.findById(id).map(d -> {
            d.setCalle(direccionEnvioRequestDTO.getCalle());
            d.setNumero(direccionEnvioRequestDTO.getNumero());
            d.setPiso(direccionEnvioRequestDTO.getPiso());
            d.setPuerta(direccionEnvioRequestDTO.getPuerta());
            d.setPoblacion(direccionEnvioRequestDTO.getPoblacion());
            d.setCp(direccionEnvioRequestDTO.getCp());
            d.setCiudad(direccionEnvioRequestDTO.getCiudad());

            return repository.save(d);

        }).orElseThrow(EntityNotFoundException::new);
    }




}
