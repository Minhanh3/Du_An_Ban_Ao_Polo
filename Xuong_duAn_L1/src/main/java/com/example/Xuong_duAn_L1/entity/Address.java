package com.example.Xuong_duAn_L1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specificAddress;
    private String phuong;
    private String quan;
    private String huyen;
    private Date dateCreated;
    private Date dateFix;
    private String creator;
    private String repairer;

}
