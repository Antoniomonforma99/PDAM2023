package com.monteroantonio.PDAM23.security.user.controller;

import com.monteroantonio.PDAM23.security.jwt.access.JwtProvider;
import com.monteroantonio.PDAM23.security.jwt.refresh.RefreshToken;
import com.monteroantonio.PDAM23.security.jwt.refresh.RefreshTokenException;
import com.monteroantonio.PDAM23.security.jwt.refresh.RefreshTokenRequest;
import com.monteroantonio.PDAM23.security.jwt.refresh.RefreshTokenService;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.security.user.dto.*;
import com.monteroantonio.PDAM23.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    //Registra al user como Usuario (Cliente)
    @PostMapping("auth/register")
    public ResponseEntity<UserResponseDTO> createUserWithUserRole(
            @RequestBody CreateUserRequestDTO createUserRequestDTO) {

        User user = service.createUserWithUserRole(createUserRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.fromUser(user));

    }


    //Registra al user como Admin
    @PostMapping("auth/register/admin")
    public ResponseEntity<UserResponseDTO> createUserWithAdminRole(
            @RequestBody CreateUserRequestDTO createUserRequestDTO) {

        User user = service.createUserWithAdminRole(createUserRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.fromUser(user));

    }

    //Login en la API
    @Transactional
    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequestDTO.getUsername(),
                                loginRequestDTO.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();

        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JwtUserResponseDTO.of(user, token, refreshToken.getToken()));

    }

    //Obtiene detalles del usuario logado
    @GetMapping("/me")
    public UserResponseDTO myProfile(@AuthenticationPrincipal User loggedUser) {
        return UserResponseDTO.fromUser(loggedUser);
    }


    //Borra un usuario
    @DeleteMapping("/user/deleteAccount")
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal User loggedUser) {
        service.delete(loggedUser);

        return ResponseEntity.noContent().build();
    }


    //Generar token de refreso
    @Transactional
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateToken(user);
                    refreshTokenService.deleteByUser(user);
                    RefreshToken refreshToken2 = refreshTokenService.createRefreshToken(user);

                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(JwtUserResponseDTO.builder()
                                    .token(token)
                                    .refreshToken(refreshToken2.getToken())
                                    .build());
                })
                .orElseThrow(() -> new RefreshTokenException("No refresh token found"));
    }


    //Cambiar contraseña del usuario
    @PutMapping("/user/changePassword")
    public UserResponseDTO changePassword(@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO,
                                          @AuthenticationPrincipal User loggedUser){
        return UserResponseDTO.fromUser(service.editPassword(loggedUser, changePasswordRequestDTO));
    }
}