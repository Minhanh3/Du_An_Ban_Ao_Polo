package com.example.Xuong_duAn_L1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    int id;
    int quantity;
    ProductDetail productDetail;
}
