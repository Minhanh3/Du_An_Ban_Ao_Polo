package com.example.Xuong_duAn_L1.repository;

import com.example.Xuong_duAn_L1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
