package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Material;
import com.example.Xuong_duAn_L1.entity.request.MaterialRequest;
import com.example.Xuong_duAn_L1.service.MaterialService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("Material")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialController {

     MaterialService materialService;

    @GetMapping({"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Material> materialPage = materialService.getAllMaterialPaged(pageable);
        model.addAttribute("list", materialPage.getContent());
        model.addAttribute("materialNew", new MaterialRequest());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", materialPage.getTotalPages());
        model.addAttribute("totalItems", materialPage.getTotalElements());
        model.addAttribute("size", size);
        return "material/home_material";
    }

    @PostMapping("add")
    public String save(@Valid @ModelAttribute Material material , BindingResult result) {
        materialService.addMaterial(material);
        log.info(material.toString());
        if (result.hasErrors()) {
                return "redirect:/Material/";
        }
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
