package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Brand;
import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.entity.dto.ProductDto;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.ProductDetailService;
import com.example.Xuong_duAn_L1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping({"", "/"})
    public String getAllSp(@RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "5") Integer size,
                           Model model) {
        Page<Product> productPage = productService.getAll(page, size);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("size", size);

        model.addAttribute("add", new ProductDto());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("colors", colorRepo.findAll());
        model.addAttribute("brands", brandRepo.findAll());
        model.addAttribute("images", imageRepo.findAll());
        model.addAttribute("materials", materialRepo.findAll());
        model.addAttribute("styles", styleRepo.findAll());
        return "product/home_product";
    }

    @PostMapping("add")
    public String addProduct(@Valid ProductDto product,
                             @RequestParam Integer idBrand,
                             @RequestParam Integer idMaterial,
                             @RequestParam Integer idStyle,
                             @RequestParam Integer idImage
            , BindingResult result
    ) {
        if (product.getImageProduct().isEmpty()) {
            result.addError(new FieldError("product", "imageProduct", "The image file is null"));
        }
        if (result.hasErrors()) {
            return "redirect:/product/";
        }
        productService.addProduct(product, idStyle, idBrand, idMaterial, idImage);
        return "redirect:/product";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("detail", product);
        return "product/detail";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductDetails(@PathVariable Integer id, Model model) {
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

    @GetMapping("/addDetail/{id}")
    public String addProductDetail(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("newDetail", new ProductDetail());
        return "product/add_product_detail";
    }

}
