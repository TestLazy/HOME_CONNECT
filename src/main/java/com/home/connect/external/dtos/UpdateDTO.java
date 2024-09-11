package com.home.connect.external.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateDTO {
    @Size(message = "15 digítos")
    @Email(message = "É inválido")
    @NotBlank(message = "Requisito")
    private String email;

    @Size(message = "25 digítos")
    @NotBlank(message = "Requisito")
    private String password;

    @Size(message = "60 dígitos")
    @NotBlank(message = "Requisito")
    private String fullName;

    @Size(message = "15 dígitos")
    @NotBlank(message = "Requisito")
    private String personalNumber;
}