package com.home.connect.intermediate.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpDTO(
        @Size(min = 5, max = 25, message = "Mínimo 5 / Máximo 25 digítos")
        @NotBlank(message = "Obrigatório")
        String username,

        @Size(min = 8, max = 15, message = "Mínimo 8 / Máximo 15 digítos")
        @NotBlank(message = "Obrigatório")
        String password,

        @Size(min = 5, max = 35, message = "Mínimo 5 / Máximo 35 digítos")
        @NotBlank(message = "Obrigatório")
        String fullName,

        @NotNull(message = "Obrigatório")
        Integer permission,

        @Size(min = 14, max = 14, message = "Mínimo 14 / Máximo 14 digítos")
        @NotBlank(message = "Obrigatório")
        String personalNumber) {}