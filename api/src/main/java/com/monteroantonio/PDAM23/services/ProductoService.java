package com.monteroantonio.PDAM23.services;

import com.monteroantonio.PDAM23.model.Categoria;
import com.monteroantonio.PDAM23.model.DTOs.producto.ProductoRequestDTO;
import com.monteroantonio.PDAM23.model.Producto;
import com.monteroantonio.PDAM23.repository.CategoriaRepository;
import com.monteroantonio.PDAM23.repository.ProductoRepository;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.services.store.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    private final CategoriaRepository categoriaRepository;

    private final StorageService storageService;

    public Page<Producto> findAll(Pageable pageable) {
        Page<Producto> result = repository.findAll(pageable);
        if (result.getContent().isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado ningún producto");
        }

        return repository.findAll(pageable);
    }

    public Producto findById(UUID id) {
        return repository.findFirstById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public Producto add(Producto producto, User loggedUser, MultipartFile file) {
        producto.setImgUrl(storageService.store(file));
        return repository.save(producto);
    }

    public Producto add (Producto producto){
        return repository.save(producto);
    }

    @Transactional
    public Producto edit(UUID id, User loggedUser, ProductoRequestDTO productoRequestDTO) {
        return repository.findById(id).map(p -> {
            p.setNombre(productoRequestDTO.getNombre());
            p.setDescripcion(productoRequestDTO.getDescripcion());
            p.setPrecio(productoRequestDTO.getPrecio());
            p.setMenus(productoRequestDTO.getMenus());
            p.setIngredientes(productoRequestDTO.getIngredientes());

            return repository.save(p);
        }).orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public Producto addCategoria(UUID id, UUID idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(EntityNotFoundException::new);

        return repository.findById(id).map(p -> {
            p.setCategoria(categoria);

            return  p;
        }).orElseThrow(EntityNotFoundException::new);

    }

    @Transactional
    public Producto deleteCategoria(UUID id, UUID idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(EntityNotFoundException::new);

        return repository.findById(id).map(p -> {
            p.setCategoria(null);

            return p;
        }).orElseThrow(EntityNotFoundException::new);

    }
}
