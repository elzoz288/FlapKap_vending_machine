package com.example.FlapKap.s_project.service;

import com.example.FlapKap.s_project.dto.BuyResponseDto;
import com.example.FlapKap.s_project.exceptions.ErrorBody;
import com.example.FlapKap.s_project.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
   String getByName(String name);
    Product addProduct(Product product,String sellerId);
    Product updateProduct(Product product,Integer id);

    String deleteProduct(String productName);

    BuyResponseDto buy(double deposit, int productId, Integer amountToBuy);


}
