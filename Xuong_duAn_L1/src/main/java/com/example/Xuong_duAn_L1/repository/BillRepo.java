package com.example.Xuong_duAn_L1.repository;

import com.example.Xuong_duAn_L1.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BillRepo extends JpaRepository<Bill,Integer> {
    @Query("SELECT b FROM Bill b WHERE (CAST(b.id AS string) LIKE %:keyword% OR b.khachhang.name LIKE %:keyword% OR CAST(b.tongtien AS string) LIKE %:keyword%) AND b.ngaytao BETWEEN :startDate AND :endDate")
    Page<Bill> findByKeywordAndDateRange(String keyword,
                                         LocalDate startDate,
                                         LocalDate endDate,
                                         Pageable page);
}
