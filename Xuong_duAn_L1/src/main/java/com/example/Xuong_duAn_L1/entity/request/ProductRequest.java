package com.example.Xuong_duAn_L1.entity.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "code is null")
    @Size(min = 3, max = 20, message = "Code must be at 3 character")
    private String code;

    @NotEmpty(message = "name is null")
    @Size(min = 3, max = 20, message = "Name must be at 3 character")
    private String name;

    private MultipartFile imageProduct;

    private Integer idMaterial;

    private Integer idStyle;

    private Integer idImage;

    private Integer idBrand;

    private LocalDate uploadDate;

    private Integer status;
}
