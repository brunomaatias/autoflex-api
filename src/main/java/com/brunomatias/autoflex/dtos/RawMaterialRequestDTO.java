package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotBlank;

public record RawMaterialRequestDTO(

        @NotBlank
        String code,

        @NotBlank
        String name,

        @NotBlank
        Integer stockQuantity
) {
}