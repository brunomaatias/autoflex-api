package com.brunomatias.autoflex.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long productId,
        String code,
        String name,
        BigDecimal price
) {}
