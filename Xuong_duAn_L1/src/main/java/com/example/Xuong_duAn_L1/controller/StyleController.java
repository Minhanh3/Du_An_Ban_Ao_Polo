package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Style;
import com.example.Xuong_duAn_L1.service.StyleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Style")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StyleController {

    StyleService styleService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Style> stylePage = styleService.getAllStylePage(pageable);

        model.addAttribute("list", stylePage.getContent());
        model.addAttribute("styleNew", new Style());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stylePage.getTotalPages());
        model.addAttribute("totalItems", stylePage.getTotalElements());
        model.addAttribute("size", size);

        return "style/home_style";
    }

    @PostMapping("add")
    public String add(Style style) {
        styleService.addStyles(style);
        return "redirect:/Style";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        styleService.deleteStyles(id);
        return "redirect:/Style";
    }
}
