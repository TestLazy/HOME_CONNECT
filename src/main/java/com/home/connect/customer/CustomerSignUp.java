package com.home.connect.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerSignUp(
        @Size(min = 5, max = 25, message = "O máximo é 25 caracteres")
        @NotBlank(message = "Obrigatório")
        String username,

        @Size(min = 8, max = 25, message = "O máximo é 25 caracteres")
        @NotBlank(message = "Obrigatório")
        String password,

        @Size(min = 5, max = 25, message = "O máximo é 25 caracteres")
        @NotBlank(message = "Obrigatório")
        String fullName,

        @Size(min = 11, max = 11, message = "O mínimo/máximo é 11 caracteres")
        @NotBlank(message = "Obrigatório")
        String personalNumber) {}