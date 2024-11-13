package com.home.connect.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerFullName(
        @Size(min = 5, max = 25, message = "O máximo é 25 caracteres")
        @NotBlank(message = "Obrigatório")
        String fullName) {}