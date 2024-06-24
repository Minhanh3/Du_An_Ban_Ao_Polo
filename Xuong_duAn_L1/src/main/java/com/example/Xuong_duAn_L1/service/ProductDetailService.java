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

    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail, Integer idSize, Integer idColor) {
        Size size = sizeRepo.findById(idSize).orElseThrow(() -> new RuntimeException("Size not found"));
        Color color = colorRepo.findById(idColor).orElseThrow(() -> new RuntimeException("Color not found"));

        productDetail.setSize(size);
        productDetail.setColor(color);

        return productDetailRepo.save(productDetail);
    }
}
