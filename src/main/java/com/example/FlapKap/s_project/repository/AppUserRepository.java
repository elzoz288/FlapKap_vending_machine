package com.example.FlapKap.s_project.repository;

import com.example.FlapKap.s_project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUserName(String username);
    boolean  existsByUserName(String username);



}
