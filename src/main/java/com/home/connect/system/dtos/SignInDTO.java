package com.home.connect.system.dtos;

import jakarta.validation.constraints.NotBlank;

public record SignInDTO(
        @NotBlank(message = "Obrigatório")
        String username,

        @NotBlank(message = "Obrigatório")
        String password) {}