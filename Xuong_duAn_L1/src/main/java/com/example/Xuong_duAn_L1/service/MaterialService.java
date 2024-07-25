package com.example.Xuong_duAn_L1.service;

import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.impl.IMaterialService;
import com.example.Xuong_duAn_L1.service.impl.IProductService;
import com.example.Xuong_duAn_L1.util.CodeGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MaterialService implements IMaterialService {
    private MaterialRepo materialRepo;

    @Override
    public List<Material> getAllMaterial() {
        return materialRepo.findAll();
    }

    @Override
    public Page<Material> getAllMaterialPaged(Pageable pageable) {
        return materialRepo.findAll(pageable);
    }

    @Override
    public Material addMaterial(Material material) {
        log.info("hien ra");
        String code = CodeGenerator.generateRandomCode(3);
        material.setCode("M_" + code);
        material.setUploadDate(LocalDate.now());
        return materialRepo.save(material);
    }

    @Override
    public Material updateMaterial(Material material) {
        String code = CodeGenerator.generateRandomCode(3);
        material.setCode("M" + code);
        material.setUploadDate(LocalDate.now());
        return materialRepo.save(material);
    }

    @Override
    public void deleteMaterial(Material material) {
        Material materialOptional = materialRepo.findById(material.getIdMaterial()).orElse(null);
        assert materialOptional != null;
        materialRepo.delete(materialOptional);
    }
}
