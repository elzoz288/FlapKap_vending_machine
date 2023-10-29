package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
     Optional<Seller> findByName(String name);
    boolean existsByName(String name);
}
