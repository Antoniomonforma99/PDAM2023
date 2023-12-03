package com.monteroantonio.PDAM23.repository;

import com.monteroantonio.PDAM23.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VentaRepository extends JpaRepository<Venta, UUID> {
}
