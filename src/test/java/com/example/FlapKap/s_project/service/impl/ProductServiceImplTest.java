package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.exceptions.BadRequestException;
import com.example.FlapKap.s_project.exceptions.ProductNotFoundException;
import com.example.FlapKap.s_project.exceptions.SellerNotFoundException;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
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
        when(productRepo.findByProductName(product.getProductName())).thenReturn(Optional.ofNullable(product));
        String tProduct=underTest.getByName(product.getProductName());
        assertThat(tProduct).isEqualTo("available amount of"+product.getAmount()+" ");
    }

    @Test
    void canAddProduct() {
        Seller seller=mock(Seller.class);
        Product product=mock(Product.class);
        given(productRepo.existsByProductName(product.getProductName())).willReturn(false);
        given(sellerRepo.findByName(seller.getName())).willReturn(Optional.ofNullable(mock(Seller.class)));

        underTest.addProduct(product, seller.getName());
        ArgumentCaptor<Product> productArgumentCaptor=ArgumentCaptor.forClass(Product.class);

        verify(productRepo).save(productArgumentCaptor.capture());
        Product capturdProduct=productArgumentCaptor.getValue();
        assertThat(capturdProduct).isEqualTo(product);
    }
    @Test
    void willThrowIfSellerNotFound() {
        Seller seller=mock(Seller.class);
        Product product=mock(Product.class);
        given(sellerRepo.findByName(product.getProductName())).willReturn(Optional.empty());
      assertThatThrownBy(()->underTest.addProduct(product, seller.getName()))
                  .isInstanceOf(SellerNotFoundException.class)
                  .hasMessageContaining("there is no seller with this name");
      verify(productRepo,never()).save(any());
    }


    @Test
    void CanUpdateProduct() {
     Product oldProduct= mock(Product.class);
     Product newProduct=mock(Product.class);
     given(productRepo.findById(1)).willReturn(Optional.ofNullable(oldProduct));
     given(productRepo.save(any(Product.class))).willReturn(newProduct);
        Product sProduct=underTest.updateProduct(newProduct,1);
     assertThat(sProduct).isNotNull();
    }

    @Test
    void throwIfThereIsNoSellerWithThisId() {
        Product product=mock(Product.class);
       given(productRepo.findById(product.getId())).willReturn(Optional.empty());
       assertThatThrownBy(()->underTest.updateProduct(product,anyInt()))
               .isInstanceOf(ProductNotFoundException.class)
               .hasMessageContaining("this product is not exist ");
       verify(productRepo,never()).save(any());
    }

    @Test
    void deleteProduct() {
        Product product=mock(Product.class);
        given(productRepo.findByProductName(product.getProductName())).willReturn(Optional.ofNullable(product));
        underTest.deleteProduct(product.getProductName());
        verify(productRepo).delete(product);
    }

    @Test
    void throwIfProductIsNotExists() {
        Product product=mock(Product.class);
        given(productRepo.findByProductName(product.getProductName())).willReturn(Optional.empty());
        assertThatThrownBy(()->underTest.deleteProduct(product.getProductName()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("this product is already deleted");
        verify(productRepo,never()).delete(any());
    }

    @Test
    void canBuy()  {
     Product product=mock(Product.class);
     given(productRepo.findById(product.getId())).willReturn(Optional.ofNullable(product));
     given(product.getAmount()).willReturn(10);
     given(product.getCoast()).willReturn(0.5);
     assertThat(underTest.buy(1.0,product.getId(),1)).isNotNull();

    }
    @Test
    void ifProductNameIsNotCorrect() {
        Product product=mock(Product.class);
        given(productRepo.findById(product.getId())).willReturn(Optional.empty());
        assertThatThrownBy(()->underTest.buy(0.3,eq(product.getId()),5))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("product name is not correct ");
        verify(productRepo,never()).save(any());
    }

    @Test
    void ifProductAmountIsNotEnough() {
        Product product=mock(Product.class);
        given(productRepo.findById(product.getId())).willReturn(Optional.ofNullable(product));
        given(product.getAmount()).willReturn(1);
        assertThatThrownBy(()->underTest.buy(0.2,eq(product.getId()),5))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("available amount is not enough");
        verify(productRepo,never()).save(any());
    }

    @Test
    void ifDepositIsNotEnough() {
        Product product=mock(Product.class);
        given(productRepo.findById(product.getId())).willReturn(Optional.ofNullable(product));
        given(product.getAmount()).willReturn(10);
        given(product.getCoast()).willReturn(0.5);
        assertThatThrownBy(()->underTest.buy(0.5,eq(product.getId()),5))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("money is not enough");
        verify(productRepo,never()).save(any());
    }

}
