package com.home.connect.customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerSignIn(
        @NotBlank(message = "Obrigatório")
        String username,

        @NotBlank(message = "Obrigatório")
        String password) {}