package com.example.Xuong_duAn_L1.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Data
public class MaterialDto {

    @NotEmpty(message = "the code is required")
    private String code;

    @NotEmpty(message = "the name is required")
    private String name;

//    @NotBlank(message = "stauts is not null")
    Integer status;

    LocalDate uploadDate;

}
