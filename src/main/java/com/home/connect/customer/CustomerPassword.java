package com.home.connect.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerPassword(
        @Size(min = 8, max = 25, message = "O máximo é 25 caracteres")
        @NotBlank(message = "Obrigatório")
        String password) {}