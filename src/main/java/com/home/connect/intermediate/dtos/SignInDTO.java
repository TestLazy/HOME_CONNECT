package com.home.connect.intermediate.dtos;

import jakarta.validation.constraints.NotBlank;

public record SignInDTO(
        @NotBlank(message = "Obrigatório")
        String username,

        @NotBlank(message = "Obrigatório")
        String password) {}