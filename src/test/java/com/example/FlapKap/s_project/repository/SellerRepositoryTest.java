package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.Role;
import com.example.FlapKap.s_project.model.Seller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepo;

    @Test
    void tryToFindSellerByName() {
      Seller seller= Seller.builder().name("momo").role(Role.SELLER).build();
      sellerRepo.save(seller);
      assertThat(sellerRepo.findByName(seller.getName())).isEqualTo(seller);
    }

    @Test
    void checkExistsOfSellerByName() {
        Seller seller= Seller.builder().name("momo").role(Role.SELLER).build();
        sellerRepo.save(seller);
        assertThat(sellerRepo.existsByName(seller.getName())).isEqualTo(true);
    }

}