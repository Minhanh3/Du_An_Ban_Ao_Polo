package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.ProductDetailService;
import com.example.Xuong_duAn_L1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ColorRepo colorRepo;

    @GetMapping("/addDetail/{id}")
    public String addProductDetail(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        List<ProductDetail> productDetails = productDetailService.getProductDetailsByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("productDetails", productDetails);
        model.addAttribute("newDetail", new ProductDetail());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("colors", colorRepo.findAll());

        return "product/add_product_detail";
    }

    @PostMapping("add")
    public String addProductDetail(@ModelAttribute ProductDetail productDetail,
                                   @RequestParam Integer idSize,
                                   @RequestParam Integer idColor,
                                   @RequestParam Integer idProduct) {
        productDetailService.addProductDetail(productDetail, idSize, idColor, idProduct);

        return "redirect:/productDetail/addDetail/" + idProduct;
    }

}
