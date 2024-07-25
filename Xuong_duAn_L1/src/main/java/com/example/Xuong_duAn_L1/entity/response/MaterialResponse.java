package com.example.Xuong_duAn_L1.entity.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MaterialResponse {

    @NotEmpty(message = "the code is required")
    private String code;

    @NotEmpty(message = "the name is required")
    private String name;

//    @NotBlank(message = "stauts is not null")
    Integer status;

    LocalDate uploadDate;

}
