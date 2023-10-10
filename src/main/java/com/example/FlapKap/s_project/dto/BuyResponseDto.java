package com.example.FlapKap.s_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BuyResponseDto {
    private double totalMoney;
    private String productName ;
    private int amount;
    private double change;

}
