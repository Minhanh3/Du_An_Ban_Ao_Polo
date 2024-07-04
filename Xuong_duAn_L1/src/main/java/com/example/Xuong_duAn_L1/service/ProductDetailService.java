package com.example.Xuong_duAn_L1.service;

import com.example.Xuong_duAn_L1.entity.Color;
import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.ProductDetail;
import com.example.Xuong_duAn_L1.entity.Size;
import com.example.Xuong_duAn_L1.repository.ColorRepo;
import com.example.Xuong_duAn_L1.repository.ProductDetailRepo;
import com.example.Xuong_duAn_L1.repository.ProductRepo;
import com.example.Xuong_duAn_L1.repository.SizeRepo;
import com.example.Xuong_duAn_L1.service.impl.IProductDetailService;
import com.example.Xuong_duAn_L1.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService {

    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ColorRepo colorRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail, Integer idSize, Integer idColor, Integer idProduct) {
        Size size = sizeRepo.findById(idSize).orElseThrow(() -> new RuntimeException("Size not found"));
        Color color = colorRepo.findById(idColor).orElseThrow(() -> new RuntimeException("Color not found"));
        Product product = productRepo.findById(idProduct).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDetail add = new ProductDetail();
        add.setSize(size);
        add.setIdSize(size.getIdSize());
        add.setColor(color);
        add.setIdColor(color.getIdColor());
        add.setProduct(product);  // Đặt đối tượng Product
        add.setIdProduct(product.getIdProduct());

        add.setPrice(productDetail.getPrice());
        add.setCode(productDetail.getCode());
        add.setInputPrice(productDetail.getInputPrice());
        add.setAmount(productDetail.getAmount());

        return productDetailRepo.save(add);
    }

    @Override
    public List<ProductDetail> getProductDetailsByProductId(Integer idProduct) {
        List<ProductDetail> details = productDetailRepo.findByIdProduct(idProduct);
        // Log để kiểm tra dữ liệu
        System.out.println("Retrieved product details: " + details);
        return details;
    }

}
