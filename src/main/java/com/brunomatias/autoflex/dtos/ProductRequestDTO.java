package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(

        Long productId,

        @NotBlank
        String code,

        @NotBlank
        String name,

        @NotNull
        BigDecimal price
) {}