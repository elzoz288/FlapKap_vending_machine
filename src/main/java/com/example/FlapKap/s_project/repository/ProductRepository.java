package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

   Optional <Product> findByProductName(String name);
    boolean existsByProductName(String email);



}
