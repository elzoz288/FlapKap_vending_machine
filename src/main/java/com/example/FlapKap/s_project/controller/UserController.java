package com.example.FlapKap.s_project.controller;

import com.example.FlapKap.s_project.dto.BuyRequestDTO;
import com.example.FlapKap.s_project.dto.BuyResponseDto;
import com.example.FlapKap.s_project.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buyer")
public class UserController {

    private final AppUserService userService;

    @PostMapping("/Deposit")
    public ResponseEntity<String> addDeposit(@NonNull HttpServletRequest request,@PathVariable Double deposit){
        return new ResponseEntity<>(userService.addDeposit(request,deposit), HttpStatus.OK);
    }
    @PostMapping("/buy")
    public ResponseEntity<BuyResponseDto> buy(@NonNull HttpServletRequest hRequest,@RequestBody BuyRequestDTO req){
        return new ResponseEntity<>(userService.buy(hRequest,req),HttpStatus.OK);
    }

    @PostMapping("/reset/{id}")
    public ResponseEntity<String>reset( @NonNull HttpServletRequest request){
        return new ResponseEntity<>(userService.reset(request),HttpStatus.OK);
    }


}
