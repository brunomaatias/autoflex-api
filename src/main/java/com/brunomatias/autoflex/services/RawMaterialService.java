package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.RawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.RawMaterialResponseDTO;
import com.brunomatias.autoflex.mapper.RawMaterialMapper;
import com.brunomatias.autoflex.models.RawMaterial;
import com.brunomatias.autoflex.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<RawMaterialResponseDTO> findAll() {
        return rawMaterialRepository.findAll(Sort.by("rawMaterialId"))
                .stream()
                .map(RawMaterialMapper::toResponseDTO)
                .toList();
    }

    public RawMaterialResponseDTO findByCode(String code) {
        return RawMaterialMapper.toResponseDTO(rawMaterialRepository.findByCode(code));
    }

    public RawMaterialResponseDTO save(RawMaterialRequestDTO dto) {
        RawMaterial rawMaterial = RawMaterialMapper.toEntity(dto);
        return RawMaterialMapper.toResponseDTO(rawMaterialRepository.save(rawMaterial));
    }

    public RawMaterialResponseDTO update(String code, RawMaterialRequestDTO dto) {
        RawMaterial existing = rawMaterialRepository.findByCode(code);

        existing.setCode(dto.code());
        existing.setName(dto.name());
        existing.setStockQuantity(dto.stockQuantity());
        RawMaterial updated = rawMaterialRepository.save(existing);

        return RawMaterialMapper.toResponseDTO(updated);
    }
}