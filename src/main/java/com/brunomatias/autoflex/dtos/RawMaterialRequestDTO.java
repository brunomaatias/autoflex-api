package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RawMaterialRequestDTO(

        Long rawMaterialId,

        @NotBlank
        String code,

        @NotBlank
        String name,

        @NotNull
        Integer stockQuantity
) {
}