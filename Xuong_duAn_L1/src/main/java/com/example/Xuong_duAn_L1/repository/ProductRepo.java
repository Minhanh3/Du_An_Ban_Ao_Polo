package com.example.Xuong_duAn_L1.repository;

import com.example.Xuong_duAn_L1.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

//    @Query(
//            value = """
//                select * from product p where (p.name = ?1 or ?1 is null or ?1 = '')
//                and (p.idMaterial = ?2 or ?2 is null or ?2 = '')
//                and idStyle = ?3
//            """,
//    nativeQuery = true
//    )
//    Page<Product> searchProduct(String productName,Integer idMaterial , Integer idStyle , Pageable pageable);

}
