package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Material;
import com.example.Xuong_duAn_L1.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("Material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Material> materialPage = materialService.getAllMaterialPaged(pageable);

        model.addAttribute("list", materialPage.getContent());
        model.addAttribute("materialNew", new Material());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", materialPage.getTotalPages());
        model.addAttribute("totalItems", materialPage.getTotalElements());
        model.addAttribute("size", size);

        return "material/home_material";
    }

    @PostMapping("add")
    public String save(Material material) {
        materialService.addMaterial(material);
        return "redirect:/Material";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            Material material = new Material();
            material.setIdMaterial(id);
            materialService.deleteMaterial(material);
            redirectAttributes.addFlashAttribute("success", "Material deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting material: " + e.getMessage());
        }
        return "redirect:/Material";
    }
}
