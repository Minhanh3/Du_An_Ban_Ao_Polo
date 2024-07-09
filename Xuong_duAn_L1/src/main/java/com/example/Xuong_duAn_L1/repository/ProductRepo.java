package com.example.Xuong_duAn_L1.repository;

import com.example.Xuong_duAn_L1.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = """
            SELECT * FROM product p 
            WHERE (p.name LIKE %:productName% OR :productName IS NULL OR :productName = '')
            AND (p.id_brand = :idBrand OR :idBrand IS NULL or :idBrand ='')
            AND (p.id_material = :idMaterial OR :idMaterial IS NULL or :idMaterial = '')
            AND (p.id_style = :idStyle OR :idStyle IS NULL or :idStyle ='')
            """, nativeQuery = true)
    Page<Product> searchProduct(String productName, Integer idBrand, Integer idMaterial, Integer idStyle, Pageable pageable);
}
