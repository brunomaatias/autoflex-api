package com.brunomatias.autoflex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "raw_material")
public class RawMaterial implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raw_material_id")
    private Long rawMaterialId;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @PrePersist
    protected void onCreate() {
        if (this.code == null || this.code.trim().isEmpty()) {
            this.code = "RWM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}