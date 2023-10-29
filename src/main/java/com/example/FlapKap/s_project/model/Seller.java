package com.example.FlapKap.s_project.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Seller {
 @Id
@GeneratedValue(strategy = IDENTITY)
private Integer id;
private String name;
@Enumerated(EnumType.STRING)
private Role role ;
}
