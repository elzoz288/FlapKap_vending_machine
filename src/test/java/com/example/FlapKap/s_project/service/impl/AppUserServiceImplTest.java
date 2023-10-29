package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.config.security.JwtService;
import com.example.FlapKap.s_project.model.AppUser;
import com.example.FlapKap.s_project.repository.AppUserRepository;
import com.example.FlapKap.s_project.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {
    @Mock
    private  AppUserRepository userRepo;
    @Mock
    private  ProductService productSer;
    @Mock
    private  JwtService jwtService;
    @InjectMocks
    private AppUserServiceImpl underTest;

    @Test
    void addDeposit() {

        HttpServletRequest req=mock(HttpServletRequest.class);
        AppUser user=mock(AppUser.class);
        String authHeader="test  value";
        String String = null;
        given(authHeader.substring(7)).willReturn(String);
        given(userRepo.findByUserName(anyString())).willReturn(Optional.ofNullable(user));
        underTest.addDeposit(req,eq(0.5));
        verify(userRepo).save(user);

    }

    @Test
    void buy() {
    }

    @Test
    void reset() {
    }
}