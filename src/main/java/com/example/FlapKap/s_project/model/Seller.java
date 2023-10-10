package com.example.FlapKap.s_project.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
public class Seller {
 @Id
 @GeneratedValue(strategy = IDENTITY)
private Integer id;
private String name;
@Enumerated(EnumType.STRING)
private Role role ;
}
