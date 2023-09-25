package com.monteroantonio.pdam.PDAM.security.user.dto;

import com.monteroantonio.pdam.PDAM.validation.annotation.FieldsDiffer;
import com.monteroantonio.pdam.PDAM.validation.annotation.FieldsMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@FieldsMatch(field = "newPassword", fieldMatch = "verifyNewPassword")
@FieldsDiffer(field = "oldPassword", fieldDiffer = "newPassword")
public class ChangePasswordRequestDTO {

    private String oldPassword, newPassword, verifyNewPassword;
}
