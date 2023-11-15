package com.monteroantonio.PDAM23.security.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monteroantonio.PDAM23.security.user.Roles;
import com.monteroantonio.PDAM23.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponseDTO {

    protected String id;

    protected String email, name, tlf;

    protected String username;

    protected Set<Roles> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;

    public static UserResponseDTO fromUser(User user) {
        return UserResponseDTO.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .username(user.getUsername())
                .name(user.getName())
                .tlf(user.getTlf())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build();
    }
}
