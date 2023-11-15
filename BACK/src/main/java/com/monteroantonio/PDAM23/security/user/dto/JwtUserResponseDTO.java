package com.monteroantonio.PDAM23.security.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monteroantonio.PDAM23.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class JwtUserResponseDTO extends UserResponseDTO{

    private String token, refreshToken;

    public JwtUserResponseDTO (UserResponseDTO userResponseDTO) {

        id = userResponseDTO.getId();
        email = userResponseDTO.getEmail();
        name = userResponseDTO.getName();
        createdAt = userResponseDTO.getCreatedAt();
        roles = userResponseDTO.getRoles();
    }
    public static JwtUserResponseDTO of(User user, String token, String refreshToken) {
        JwtUserResponseDTO result = new JwtUserResponseDTO(UserResponseDTO.fromUser(user));
        result.setToken(token);
        result.setRefreshToken(refreshToken);

        return result;
    }
}
