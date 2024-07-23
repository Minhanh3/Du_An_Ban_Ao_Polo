package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Size;
import com.example.Xuong_duAn_L1.service.SizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Size")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeController {
    SizeService sizeService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Size> sizePage = sizeService.getAllSizePage(pageable);

        model.addAttribute("list", sizePage.getContent());
        model.addAttribute("sizeNew", new Size());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sizePage.getTotalPages());
        model.addAttribute("totalItems", sizePage.getTotalElements());
        model.addAttribute("size", size);
        return "size/home_size";
    }

    @PostMapping("add")
    public String add(@RequestParam("name") String name,
                      @RequestParam(value = "customSize", required = false) String customSize,
                      @RequestParam("status") Integer status,
                      RedirectAttributes redirectAttributes) {
        try {
            String sizeName = name.equals("custom") ? customSize : name;
            if (sizeName == null || sizeName.trim().isEmpty()) {
                throw new Exception("Vui lòng chọn hoặc nhập size");
            }
            Size size = new Size();
            size.setName(sizeName);
            size.setStatus(status);
            sizeService.addSize(size);
            redirectAttributes.addFlashAttribute("successMessage", "Size đã được thêm thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/Size";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        sizeService.deleteSize(id);
        return "redirect:/Size";
    }
}
