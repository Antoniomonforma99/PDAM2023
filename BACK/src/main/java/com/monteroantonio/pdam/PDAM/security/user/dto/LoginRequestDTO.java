package com.monteroantonio.pdam.PDAM.security.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginRequestDTO {

    private String email, password;

}
