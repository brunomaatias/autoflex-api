package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RawMaterialRequestDTO(

        Long rawMaterialId,

        String code,

        @NotBlank
        String name,

        @NotNull
        @PositiveOrZero
        Integer stockQuantity
) {
}