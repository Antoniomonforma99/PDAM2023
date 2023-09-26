package com.monteroantonio.pdam.PDAM.security.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CreateUserRequestDTO {

    private String name;
    private String email;
    private String username;
    private String password;
    private String verifyPassword;
    private String tlf;
}
