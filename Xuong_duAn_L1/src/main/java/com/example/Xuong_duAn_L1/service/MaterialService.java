package com.example.Xuong_duAn_L1.service;

import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.impl.IMaterialService;
import com.example.Xuong_duAn_L1.service.impl.IProductService;
import com.example.Xuong_duAn_L1.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService implements IMaterialService {

    @Autowired
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
        String code;
        code = CodeGenerator.generateRandomCode(3);
        material.setCode("M" + code);
        material.setUploadDate(LocalDate.now());
        return materialRepo.save(material);
    }

    @Override
    public Material updateMaterial(Material material) {
        String code;
        code = CodeGenerator.generateRandomCode(3);
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
