package com.home.connect.customer;

import jakarta.validation.constraints.Size;

public record CustomerDTO(
        @Size(min = 5, max = 25, message = "O máximo é 25 caracteres")
        String username,

        @Size(min = 8, max = 25, message = "O máximo é 25 caracteres")
        String password,

        @Size(min = 5, max = 25, message = "O máximo é 25 caracteres")
        String fullName) {}