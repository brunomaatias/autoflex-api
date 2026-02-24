package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductRawMaterialRequestDTO(
        @NotNull
        Long productId,

        @NotNull
        Long rawMaterialId,

        @NotNull
        Integer requiredQuantity
) {
}
