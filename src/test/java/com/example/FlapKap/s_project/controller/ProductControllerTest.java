package com.example.FlapKap.s_project.controller;

import com.example.FlapKap.s_project.model.Product;
import com.example.FlapKap.s_project.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    private Product product;

    @BeforeEach
    public void init(){
       product= Product.builder().productName("pepsi").amount(5).build();
    }


    @Test
    public void productController_getAllProduct()throws Exception {
    }

    @Test
    void getByName() {
    }

    @Test
    void addProduct() throws Exception {
        given(productService.addProduct(ArgumentMatchers.any(),ArgumentMatchers.any())).willAnswer(InvocationOnMock::getArguments);
        ResultActions response=mockMvc.perform(post("/product/prime/add/{name}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}