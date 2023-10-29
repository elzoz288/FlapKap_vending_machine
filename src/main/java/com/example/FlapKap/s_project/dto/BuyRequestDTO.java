package com.example.FlapKap.s_project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyRequestDTO {
    private int productId;
    private int amount;
}
