package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepo;

    @Test
    void TryToFindByUserName() {
        AppUser appUser= AppUser.builder().userName("mohamed").password("1234").build();
        appUserRepo.save(appUser);
        Optional<AppUser> user= Optional.of(appUser);
        assertThat(appUserRepo.findByUserName(appUser.getUsername())).isEqualTo(user);
    }

    @Test
    void checkExistsByUserName() {
        AppUser appUser= AppUser.builder().userName("mohamed").password("1234").build();
        appUserRepo.save(appUser);
        assertThat(appUserRepo.existsByUserName(appUser.getUsername())).isEqualTo(true);
    }
}