package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.config.security.JwtService;
import com.example.FlapKap.s_project.dto.BuyRequestDTO;
import com.example.FlapKap.s_project.dto.BuyResponseDto;
import com.example.FlapKap.s_project.dto.DepositRequestDto;
import com.example.FlapKap.s_project.exceptions.BadRequestException;
import com.example.FlapKap.s_project.model.AppUser;
import com.example.FlapKap.s_project.repository.AppUserRepository;
import com.example.FlapKap.s_project.service.AppUserService;
import com.example.FlapKap.s_project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepo;
    private final ProductService productSer;
    private final JwtService jwtService;
    @Override
    public String addDeposit(@NonNull HttpServletRequest request, Double deposit) {
        final String authHeader = request.getHeader("Authorization");
        final  String jwt = authHeader.substring(7);
        String username=jwtService.extractUsername(jwt);
        AppUser user=userRepo.findByUserName(username)
                  .orElseThrow(()-> new RuntimeException("the name is not correct "));
        user.setDeposit(deposit);
        userRepo.save(user);
        return "you have "+deposit+" cent to buy with it ";
    }

    @Override
    public BuyResponseDto buy( @NonNull HttpServletRequest request,BuyRequestDTO req) {
        final String authHeader = request.getHeader("Authorization");
        final  String jwt = authHeader.substring(7);
        String username=jwtService.extractUsername(jwt);
        Double x =userRepo.findByUserName(username).get().getDeposit();
        return productSer.buy(x, req.getProductId(), req.getAmount());
    }

    @Override
    public String reset( @NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final  String jwt = authHeader.substring(7);
        String username=jwtService.extractUsername(jwt);
        AppUser user=userRepo.findByUserName(username)
                .orElseThrow(()-> new RuntimeException("the name is not correct "));
        user.setDeposit(0.0);
        userRepo.save(user);
        return "you have 0 cent to buy with it ";
    }


}
