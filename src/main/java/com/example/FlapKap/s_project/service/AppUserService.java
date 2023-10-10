package com.example.FlapKap.s_project.service;

import com.example.FlapKap.s_project.dto.BuyRequestDTO;
import com.example.FlapKap.s_project.dto.BuyResponseDto;
import com.example.FlapKap.s_project.dto.DepositRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;

public interface AppUserService {
    String addDeposit(@NonNull HttpServletRequest request,Double deposit);
    BuyResponseDto buy(BuyRequestDTO req);

    String reset(@NonNull HttpServletRequest request);

}
