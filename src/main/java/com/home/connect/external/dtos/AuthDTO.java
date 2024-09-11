package com.home.connect.external.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class AuthDTO {
    @Size(max = 15, message = "15 digítos")
    @Email(message = "Inválido")
    @NotBlank(message = "Requisito")
    private String email;

    @Size(max = 25, message = "25 digítos")
    @NotBlank(message = "Requisito")
    private String password;

    @Size(max = 60, message = "60 dígitos")
    @NotBlank(message = "Requisito")
    private String fullName;

    @Max(value = 2, message = "Entre (1 - 2)")
    @NotNull(message = "Requisito")
    private Integer permission;

    @Size(max = 15, message = "15 dígitos")
    @NotBlank(message = "Requisito")
    private String personalNumber;
}