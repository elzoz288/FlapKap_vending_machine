package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.dto.BuyResponseDto;
import com.example.FlapKap.s_project.exceptions.BadRequestException;
import com.example.FlapKap.s_project.exceptions.ProductNotFoundException;
import com.example.FlapKap.s_project.exceptions.SellerNotFoundException;
import com.example.FlapKap.s_project.model.Product;
import com.example.FlapKap.s_project.model.Seller;
import com.example.FlapKap.s_project.repository.ProductRepository;
import com.example.FlapKap.s_project.repository.SellerRepository;
import com.example.FlapKap.s_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final SellerRepository sellerRepo;
    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public String getByName(String name) {
        Product product=productRepo.findByProductName(name)
                .orElseThrow(()->new ProductNotFoundException("! Sorry this product is not available now, check us soon "));
        return "available amount of"+product.getAmount()+" ";
    }

    @Override
    public Product addProduct(Product product,String name) {
        if (productRepo.existsByProductName(product.getProductName())) throw new BadRequestException("product is already exist");
        Seller seller=sellerRepo.findByName(name).orElseThrow(()->new SellerNotFoundException("there is no seller with this name"));
        product.setSeller(seller);
        return productRepo.save(product);

    }

    @Override
    public Product updateProduct(Product product, Integer id) {
        Product testProduct=productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException("this product is not exist "));
        testProduct.setProductName(product.getProductName());
        testProduct.setAmount(product.getAmount());
        testProduct.setSeller(product.getSeller());

        return productRepo.save(testProduct);
    }

    @Override
    public String deleteProduct(String productName) {
        Product product=productRepo.findByProductName(productName)
                .orElseThrow(()->new ProductNotFoundException("this product is already deleted "));
        productRepo.delete(product);
        return "product "+productName+" deleted successfully ";
    }

    @Override
    public BuyResponseDto buy(double deposit, int productId, Integer amountToBuy) throws BadRequestException {
       Product product=productRepo.findById(productId)
               .orElseThrow(()->new ProductNotFoundException("product name is not correct "));
       if (product.getAmount()<amountToBuy) {throw new BadRequestException("available amount is not enough");}
       else if (!(deposit>(product.getCoast()*amountToBuy))){throw new BadRequestException("money is not enough");}
       int newAmount=product.getAmount()-amountToBuy;
       product.setAmount(newAmount);
       productRepo.save(product);
       double total=amountToBuy * product.getCoast();
        BuyResponseDto buyRes = BuyResponseDto.builder().totalMoney(total)
                .amount(amountToBuy).productName(product.getProductName()).change(deposit-total).build();
        return buyRes;
    }
}
