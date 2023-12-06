package com.monteroantonio.PDAM23.security.user.repository;

import com.monteroantonio.PDAM23.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUsername(String email);
}
