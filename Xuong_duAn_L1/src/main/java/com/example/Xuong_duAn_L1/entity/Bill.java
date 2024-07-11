package com.example.Xuong_duAn_L1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Date;

@Entity
@Table(name = "Bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private Employee nhanvien;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private Customer khachhang;
    int tongtien;

    private String trangthai;
    private String kieu;
    private Date ngaynhan;
    private Date ngayship;
    private String diachinhanhang;
    private String ghichu;
    private Date ngaytao;
    private Date ngaysua;
    private String nguoitao;
    private String nguoisua;
}
