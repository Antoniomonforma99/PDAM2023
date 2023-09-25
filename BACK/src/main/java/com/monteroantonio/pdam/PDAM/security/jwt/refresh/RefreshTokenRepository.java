package com.monteroantonio.pdam.PDAM.security.jwt.refresh;

import com.monteroantonio.pdam.PDAM.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, User> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
