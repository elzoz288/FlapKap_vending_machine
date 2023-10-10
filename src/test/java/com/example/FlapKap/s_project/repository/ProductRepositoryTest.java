package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepo;

    @Test
    void tryToFindByProductName() {
      Product product= Product.builder().amount(5).productName("pepsi").coast(.5).seller(null).build();
      productRepo.save(product);
      assertThat(productRepo.findByProductName(product.getProductName())).isEqualTo(product);
    }

    @Test
    void checkExistsByName() {
        Product product= Product.builder().amount(5).productName("pepsi").coast(.5).seller(null).build();
        productRepo.save(product);
        assertThat(productRepo.existsByProductName(product.getProductName())).isEqualTo(true);
    }
}