package com.home.connect.intermediate.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignDTO(
        @Size(min = 5, max = 25, message = "Mínimo 5 / Máximo 25 digítos")
        @NotBlank(message = "Obrigatório")
        String username,

        @Size(min = 8, max = 15, message = "Mínimo 8 / Máximo 15 digítos")
        @NotBlank(message = "Obrigatório")
        String password,

        @Size(min = 5, max = 35, message = "Mínimo 5 / Máximo 35 digítos")
        @NotBlank(message = "Obrigatório")
        String fullName) {}