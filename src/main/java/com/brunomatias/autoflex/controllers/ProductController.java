package com.brunomatias.autoflex.controllers;

import com.brunomatias.autoflex.dtos.ProductRequestDTO;
import com.brunomatias.autoflex.dtos.ProductResponseDTO;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable String code) {
        return ResponseEntity.ok(productService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO dto) {
        ProductResponseDTO created = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String code, @RequestBody @Valid ProductRequestDTO dto) {
        ProductResponseDTO updated = productService.update(code, dto);
        return ResponseEntity.ok(updated);
    }
}
