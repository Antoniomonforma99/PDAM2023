package com.monteroantonio.pdam.PDAM.security.user.repository;

import com.monteroantonio.pdam.PDAM.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);
}
