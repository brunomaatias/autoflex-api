package com.brunomatias.autoflex.mapper;

import com.brunomatias.autoflex.dtos.ProductRequestDTO;
import com.brunomatias.autoflex.dtos.ProductResponseDTO;
import com.brunomatias.autoflex.models.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setCode(dto.code());
        product.setName(dto.name());
        product.setPrice(dto.price());
        return product;
    }

    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getCode(),
                product.getName(),
                product.getPrice()
        );
    }
}