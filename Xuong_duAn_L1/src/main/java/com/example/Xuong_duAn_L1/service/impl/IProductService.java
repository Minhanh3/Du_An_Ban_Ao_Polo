package com.example.Xuong_duAn_L1.service.impl;

import com.example.Xuong_duAn_L1.entity.Product;
import com.example.Xuong_duAn_L1.entity.request.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();

    Product findProductById(int id);

    void addProduct(ProductRequest productRequest, Integer idStyle,
                    Integer idImage,
                    Integer idMaterial,
                    Integer idBrand);
    Page<Product> getAll(int pageNo, int pageSize);
    void delete(Integer id);
}
