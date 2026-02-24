package com.brunomatias.autoflex.repositories;

import com.brunomatias.autoflex.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    RawMaterial findByCode(String code);
}
