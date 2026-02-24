package com.brunomatias.autoflex.dtos;

public record ProductRawMaterialResponseDTO(
        Long productRawMaterialId,
        Long productId,
        String productName,
        Long rawMaterialId,
        String rawMaterialName,
        Integer requiredQuantity
) {}
