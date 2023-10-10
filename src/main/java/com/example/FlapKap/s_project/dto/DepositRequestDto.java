package com.example.FlapKap.s_project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositRequestDto {
    private String username;
    private Double deposit;
}
