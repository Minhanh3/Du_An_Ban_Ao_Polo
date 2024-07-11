package com.example.Xuong_duAn_L1.repository;

import com.example.Xuong_duAn_L1.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillDetailRepo extends JpaRepository<BillDetail, Integer> {
    @Query("SELECT bill FROM BillDetail bill WHERE bill.bill.id = ?1")
    public List<BillDetail> findByIDBill(Long id);

}
