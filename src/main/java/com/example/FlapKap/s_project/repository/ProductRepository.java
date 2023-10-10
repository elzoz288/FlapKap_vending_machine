package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByProductName(String name);
    boolean existsByProductName(String email);



}
