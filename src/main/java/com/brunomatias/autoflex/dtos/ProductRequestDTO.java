package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(

        Long productId,

        String code,

        @NotBlank
        String name,

        @NotNull
        @Positive
        BigDecimal price
) {}