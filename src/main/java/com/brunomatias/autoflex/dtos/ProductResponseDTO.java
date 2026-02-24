package com.brunomatias.autoflex.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String code,
        String name,
        BigDecimal price
) {}
