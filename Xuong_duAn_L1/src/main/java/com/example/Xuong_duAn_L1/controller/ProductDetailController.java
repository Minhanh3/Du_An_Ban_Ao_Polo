package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.ProductDetailService;
import com.example.Xuong_duAn_L1.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("productDetail")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailController {

    private ProductService productService;
    private ProductDetailService productDetailService;
    private BrandRepo brandRepo;
    private ImageRepo imageRepo;
    private MaterialRepo materialRepo;
    private StyleRepo styleRepo;
    private SizeRepo sizeRepo;
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

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        ProductDetail productDetail = productDetailService.getProductDetailById(id);
        if (productDetail == null) {
            return "redirect:/product";
        }

        model.addAttribute("productDetail", productDetail);
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("colors", colorRepo.findAll());

        return "product/edit_product_detail";
    }

    @PostMapping("update")
    public String updateProductDetail(@ModelAttribute ProductDetail productDetail,
                                      @RequestParam Integer idSize,
                                      @RequestParam Integer idColor,
                                      @RequestParam Integer idProduct) {
        productDetailService.updateProductDetail(productDetail, idSize, idColor, idProduct);
        return "redirect:/productDetail/addDetail/" + idProduct;
    }

    @GetMapping("delete/{id}")
    public String deleteProductDetail(@PathVariable int id) {
        // Lấy ProductDetail trước khi xóa
        ProductDetail productDetail = productDetailService.getProductDetailById(id);
        if (productDetail != null) {
            Integer idProduct = productDetail.getProduct().getIdProduct();
            productDetailService.deleteProductDetailById(id);
            return "redirect:/productDetail/addDetail/" + idProduct;
        } else {
            // Xử lý trường hợp không tìm thấy ProductDetail
            return "redirect:/product"; // hoặc trang lỗi phù hợp
        }
    }
}
