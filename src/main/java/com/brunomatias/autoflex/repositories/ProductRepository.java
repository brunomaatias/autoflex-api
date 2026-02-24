package com.brunomatias.autoflex.repositories;

import com.brunomatias.autoflex.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);

    List<Product> findAllByOrderByPriceDesc();
}
