package com.monteroantonio.PDAM23.security.jwt.refresh;
import org.springframework.transaction.annotation.Transactional;

import com.monteroantonio.PDAM23.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Transactional
    int deleteByUser(User user);
}