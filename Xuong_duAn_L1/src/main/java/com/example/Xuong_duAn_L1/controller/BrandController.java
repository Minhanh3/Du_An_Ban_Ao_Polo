package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Brand;
import com.example.Xuong_duAn_L1.service.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Brand")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {

//    @Autowired
    BrandService brandService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Brand> brandPage = brandService.getAllBrandPage(pageable);

        model.addAttribute("list", brandPage.getContent());
        model.addAttribute("brandNew", new Brand());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", brandPage.getTotalPages());
        model.addAttribute("totalItems", brandPage.getTotalElements());
        model.addAttribute("size", size);
        return "brand/home_brand";
    }

    @PostMapping("add")
    public String add(Brand brand) {
        brandService.addBrand(brand);
        return "redirect:/Brand";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        brandService.deleteBrand(id);
        return "redirect:/Brand";
    }
}
