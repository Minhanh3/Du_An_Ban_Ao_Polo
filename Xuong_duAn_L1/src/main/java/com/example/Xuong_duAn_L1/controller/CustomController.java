package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.repository.CustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomController {

    @Autowired
    private CustomRepo repo;

    @GetMapping("/view")
    public String view(Model model){
        model.addAttribute("list", repo.findAll());
        return "";
    }

}
