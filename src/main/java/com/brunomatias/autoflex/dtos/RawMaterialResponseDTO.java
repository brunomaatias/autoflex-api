package com.brunomatias.autoflex.dtos;

public record RawMaterialResponseDTO(
        Long rawMaterialId,
        String code,
        String name,
        Integer stockQuantity
) {
}