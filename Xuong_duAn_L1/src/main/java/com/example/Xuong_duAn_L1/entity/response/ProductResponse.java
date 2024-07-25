package com.example.Xuong_duAn_L1.entity.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductResponse {

     Integer idProduct;
     String code;
     String name;
     String imageProduct;
     Integer idMaterial;
     Integer idStyle;
     Integer idImage;
     Integer idBrand;
     LocalDate uploadDate;
     Integer status;
}
