package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Brand;
import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.ProductDetailService;
import com.example.Xuong_duAn_L1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

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
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ColorRepo colorRepo;

    @GetMapping("")
    public String getAllSp(@RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 1) {
            size = 5;
        }
        Page<Product> productPage = productService.getAll(page, size);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("size", size);

        model.addAttribute("add", new Product());
        model.addAttribute("addct", new ProductDetail());
        model.addAttribute("listspct", productDetailService.getAll());
        model.addAttribute("size", sizeRepo.findAll());
        model.addAttribute("color", colorRepo.findAll());
        model.addAttribute("brand", brandRepo.findAll());
        model.addAttribute("image", imageRepo.findAll());
        model.addAttribute("material", materialRepo.findAll());
        model.addAttribute("style", styleRepo.findAll());
        return "product/home_product";
    }

    @PostMapping("add")
    public String addProduct(Product product,
                             @RequestParam Integer idBrand,
                             @RequestParam Integer idMaterial,
                             @RequestParam Integer idStyle,
                             @RequestParam Integer idImage
    ) {
        productService.addProduct(product, idStyle, idBrand, idMaterial, idImage);
        return "redirect:/product";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("detail", product);
        return "product/detail";
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductDetails(@PathVariable Integer id , Model model) {
        try {
            List<ProductDetail> details = productDetailService.getProductDetailsByProductId(id);
            model.addAttribute("details", details);
            for (ProductDetail detail : details) {
                System.out.println("Product: " + detail.getProduct());
                System.out.println("Size: " + detail.getSize());
                System.out.println("Color: " + detail.getColor());
            }
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lấy chi tiết sản phẩm: " + e.getMessage());
        }
    }
}
