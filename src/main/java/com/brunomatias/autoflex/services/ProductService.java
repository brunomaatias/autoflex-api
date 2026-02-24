package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.ProductRequestDTO;
import com.brunomatias.autoflex.dtos.ProductResponseDTO;
import com.brunomatias.autoflex.mapper.ProductMapper;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    public List<ProductResponseDTO> findAllByOrderByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    public ProductResponseDTO findByCode(String code) {
        Product product = productRepository.findByCode(code);
        return ProductMapper.toResponseDTO(product);
    }

    public ProductResponseDTO save(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return ProductMapper.toResponseDTO(productRepository.save(product));
    }

    public ProductResponseDTO update(String code, ProductRequestDTO dto) {
        Product existing = productRepository.findByCode(code);

        existing.setCode(dto.code());
        existing.setName(dto.name());
        existing.setPrice(dto.price());
        Product updated = productRepository.save(existing);

        return ProductMapper.toResponseDTO(updated);
    }
}