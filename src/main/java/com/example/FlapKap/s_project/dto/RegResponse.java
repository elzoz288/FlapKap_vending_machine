package com.example.FlapKap.s_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegResponse {
    private String userName;
    private Double deposit=0.0;
    private String token;
}
