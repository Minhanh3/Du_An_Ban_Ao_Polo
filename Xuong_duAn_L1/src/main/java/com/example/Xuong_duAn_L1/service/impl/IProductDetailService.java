package com.example.Xuong_duAn_L1.service.impl;

import com.example.Xuong_duAn_L1.entity.ProductDetail;

import java.util.List;

public interface IProductDetailService {

    List<ProductDetail> getAll();

    ProductDetail addProductDetail(ProductDetail productDetail , Integer idSize , Integer idColor);

    List<ProductDetail> getProductDetailsByProductId(Integer productId);
}
