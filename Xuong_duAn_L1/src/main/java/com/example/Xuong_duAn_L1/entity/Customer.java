package com.example.Xuong_duAn_L1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private int phone;
    private int passWord;
    private LocalDateTime dateCreated;
    private LocalDateTime dateFix;
    private String creator;
    private String repairer;

    @PrePersist
    protected void onCreate() {
        dateCreated = LocalDateTime.now();
        dateFix = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateFix = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "idAddress", referencedColumnName = "id")
    private Address address;
}
