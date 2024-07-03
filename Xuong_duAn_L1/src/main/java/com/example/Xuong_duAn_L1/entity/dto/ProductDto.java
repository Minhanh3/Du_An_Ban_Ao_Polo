package com.example.Xuong_duAn_L1.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ProductDto {

    @NotEmpty(message = "code is null")
    private String code;

    @NotEmpty(message = "name is null")
    private String name;

    private MultipartFile imageProduct;

    private Integer idMaterial;

    private Integer idStyle;

    private Integer idImage;

    private Integer idBrand;

    private LocalDate uploadDate;

    private Integer status;
}
