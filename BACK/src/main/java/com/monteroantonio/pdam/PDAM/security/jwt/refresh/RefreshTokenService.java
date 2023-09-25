package com.monteroantonio.pdam.PDAM.security.jwt.refresh;

import com.monteroantonio.pdam.PDAM.security.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(durationInMinutes * 60));

        refreshToken = repository.save(refreshToken);


        return refreshToken;
    }

    public RefreshToken verify(RefreshToken refreshToken) {
        //Si el token ha caducado, lo eliminamos
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            repository.delete(refreshToken);
            throw new RefreshTokenException("Expired refresh token: " + refreshToken.getToken() + ". Please, login again");
        }

        return refreshToken;


    }

    @Transactional
    public int deleteByUser(User user) {
        return repository.deleteByUser(user);
    }
}
