package com.home.connect.property;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PropertyRequest(
        @NotNull(message = "Obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Deve ser maior que zero")
        BigDecimal value,

        @NotBlank(message = "Obrigatório")
        String description) {}