package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.repository.BrandRepo;
import com.example.Xuong_duAn_L1.repository.ImageRepo;
import com.example.Xuong_duAn_L1.repository.MaterialRepo;
import com.example.Xuong_duAn_L1.repository.StyleRepo;
import com.example.Xuong_duAn_L1.service.ProductDetailService;
import com.example.Xuong_duAn_L1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("productDetail")
public class ProductDetailController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private StyleRepo styleRepo;

    //    @GetMapping("")
//    public String getAllSp(@RequestParam(defaultValue = "0") Integer page,
//                           @RequestParam(defaultValue = "10") Integer size,
//                           Model model) {
//        if (page == null || page < 0) {
//            page = 0;
//        }
//        if (size == null || size < 1) {
//            size = 5;
//        }
//        Page<Product> productPage = productService.getAll(page, size);
//        model.addAttribute("list", productPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", productPage.getTotalPages());
//        model.addAttribute("totalItems", productPage.getTotalElements());
//        model.addAttribute("size", size);
//
////        model.addAttribute("addct", new ProductDetail());
//        model.addAttribute("brand", brandRepo.findAll());
//        model.addAttribute("image", imageRepo.findAll());
//        model.addAttribute("material", materialRepo.findAll());
//        model.addAttribute("style", styleRepo.findAll());
//        return "product/home_product";
//    }

//    @PostMapping("addCt")
//    public String addProduct(Product product,
//                             @RequestParam Integer idBrand,
//                             @RequestParam Integer idMaterial,
//                             @RequestParam Integer idStyle,
//                             @RequestParam Integer idImage
//    ) {
//        productService.addProduct(product, idStyle, idBrand, idMaterial, idImage);
//        return "redirect:/product";
//    }

    @PostMapping("add")
    public String addProductDetail(ProductDetail productDetail,
                                   @RequestParam Integer idSize,
                                   @RequestParam Integer idColor
    ) {
        productDetailService.addProductDetail(productDetail, idSize, idColor);
        return "redirect:/product";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("detail", product);
        return "product/detail";
    }

}
