package com.brunomatias.autoflex.dtos;

public record RawMaterialResponseDTO(
        String code,
        String name,
        Integer stockQuantity
) {
}