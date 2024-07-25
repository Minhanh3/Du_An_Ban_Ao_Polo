package com.example.Xuong_duAn_L1.entity.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Data
public class MaterialRequest {

    @NotEmpty(message = "the code is required")
    private String code;

    @NotEmpty(message = "the name is required")
    private String name;

//    @NotBlank(message = "stauts is not null")
    Integer status;

    LocalDate uploadDate;

}
