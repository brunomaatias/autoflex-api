package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.ProductRequestDTO;
import com.brunomatias.autoflex.dtos.ProductResponseDTO;
import com.brunomatias.autoflex.dtos.ProductionSuggestionDTO;
import com.brunomatias.autoflex.mapper.ProductMapper;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.models.ProductRawMaterial;
import com.brunomatias.autoflex.models.RawMaterial;
import com.brunomatias.autoflex.repositories.ProductRawMaterialRepository;
import com.brunomatias.autoflex.repositories.ProductRepository;
import com.brunomatias.autoflex.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private ProductRawMaterialRepository productRawMaterialRepository;

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    public List<ProductResponseDTO> findAllByOrderByPrice() {
        return productRepository.findAllByOrderByPrice()
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

    public List<ProductionSuggestionDTO> calculateProductionPlan() {
        List<ProductionSuggestionDTO> result = new ArrayList<>();
        List<Product> products = productRepository.findAllByOrderByPrice();

        Map<Long, Integer> stockMap = rawMaterialRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        RawMaterial::getRawMaterialId,
                        RawMaterial::getStockQuantity
                ));

        for (Product product : products) {
            List<ProductRawMaterial> relations =
                    productRawMaterialRepository.findByProductId(product.getProductId());

            int maxProduction = Integer.MAX_VALUE;

            for (ProductRawMaterial relation : relations) {
                int available = stockMap.getOrDefault(
                        relation.getRawMaterial().getRawMaterialId(),
                        0
                );
                int possible = available / relation.getRequiredQuantity();

                maxProduction = Math.min(maxProduction, possible);
            }

            if (maxProduction > 0) {
                for (ProductRawMaterial relation : relations) {
                    Long rawId = relation.getRawMaterial().getRawMaterialId();
                    int used = relation.getRequiredQuantity() * maxProduction;

                    stockMap.put(rawId, stockMap.get(rawId) - used);
                }

                result.add(new ProductionSuggestionDTO(
                        product.getProductId(),
                        product.getName(),
                        maxProduction,
                        product.getPrice(),
                        product.getPrice().multiply(BigDecimal.valueOf(maxProduction))
                ));
            }
        }
        return result;
    }
}