package com.example.Xuong_duAn_L1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSize;
    private String code;

    String name ;

    Integer status;

    LocalDate uploadDate;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "size")
    Set<ProductDetail> productDetailSet;
}
