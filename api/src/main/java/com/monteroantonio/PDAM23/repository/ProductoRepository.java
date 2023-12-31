package com.monteroantonio.PDAM23.repository;

import com.monteroantonio.PDAM23.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    public Optional<Producto> findFirstById(UUID id);





}
