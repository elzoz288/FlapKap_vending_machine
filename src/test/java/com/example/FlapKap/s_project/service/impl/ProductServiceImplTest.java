package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.model.Product;
import com.example.FlapKap.s_project.model.Seller;
import com.example.FlapKap.s_project.repository.ProductRepository;
import com.example.FlapKap.s_project.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;
    @Mock
    private SellerRepository sellerRepo;
    @InjectMocks
    private ProductServiceImpl underTest;

    @Test
    void tryToGetAllProducts() {
        List<Product> products= Collections.singletonList(mock(Product.class));
        when(productRepo.findAll()).thenReturn(products);
        List<Product> tProducts=underTest.getAllProduct();
        assertThat(tProducts).isNotNull();
        assertThat(tProducts).isEqualTo(products);
    }

    @Test
    void tryToGetProductByName() {
        Product product=mock(Product.class);
        when(productRepo.findByProductName(product.getProductName())).thenReturn(product);
        String tProduct=underTest.getByName(product.getProductName());
        assertThat(tProduct).isEqualTo("available amount of"+product.getAmount()+" ");
    }

    @Test
    void addProduct() {

        Product product = Product.builder()
                .productName("pepsi").amount(25).coast(0.5).seller(null).build();
        when(sellerRepo.existsByName("moh")).thenReturn(true);
        when(sellerRepo.findByName("moh")).thenReturn(mock(Seller.class));
        underTest.addProduct(product,"moh");

        ArgumentCaptor<Product> productArgumentCaptor=ArgumentCaptor.forClass(Product.class);
        verify(productRepo).save(productArgumentCaptor.capture());
        Product capturdProduct=productArgumentCaptor.getValue();
        assertThat(capturdProduct).isEqualTo(product);


    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void buy() {
    }
}