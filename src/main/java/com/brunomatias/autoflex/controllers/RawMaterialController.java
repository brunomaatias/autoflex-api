package com.brunomatias.autoflex.controllers;

import com.brunomatias.autoflex.dtos.RawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.RawMaterialResponseDTO;
import com.brunomatias.autoflex.services.RawMaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @GetMapping
    public ResponseEntity<List<RawMaterialResponseDTO>> getAllRawMaterials() {
        return ResponseEntity.ok(rawMaterialService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<RawMaterialResponseDTO> getRawMaterialByCode(@PathVariable String code) {
        return ResponseEntity.ok(rawMaterialService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<RawMaterialResponseDTO> createRawMaterial(@RequestBody @Valid RawMaterialRequestDTO dto) {
        RawMaterialResponseDTO created = rawMaterialService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{code}")
    public ResponseEntity<RawMaterialResponseDTO> updateRawMaterial(@PathVariable String code, @RequestBody @Valid RawMaterialRequestDTO dto) {
        RawMaterialResponseDTO updated = rawMaterialService.update(code, dto);
        return ResponseEntity.ok(updated);
    }
}
