package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Color;
import com.example.Xuong_duAn_L1.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/Color")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {

    //    @Autowired
    ColorService colorService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "6") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Color> ColorPage = colorService.getAllColorPage(pageable);

        model.addAttribute("list", ColorPage.getContent());
        model.addAttribute("colorNew", new Color());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ColorPage.getTotalPages());
        model.addAttribute("totalItems", ColorPage.getTotalElements());
        model.addAttribute("size", size);

        model.addAttribute("availableColors", Arrays.asList("white", "black", "wine", "yellow", "olive", "navy"));

        return "color/home_color";
    }

    @PostMapping("add")
    public String add(Color Color) {
        colorService.addColor(Color);
        return "redirect:/Color";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        colorService.deleteColor(id);
        return "redirect:/Color";
    }

}
