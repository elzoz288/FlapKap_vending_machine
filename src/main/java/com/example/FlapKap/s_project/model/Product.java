package com.example.FlapKap.s_project.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
@Table(name = "avail_products")
//@NamedQuery(name="Product.getAllCount",query = "select count(product_name) from Product")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id")
    private Integer id;
    private int amount;
    private String productName;
    private double coast;
    @ManyToOne
    private Seller seller;
}
