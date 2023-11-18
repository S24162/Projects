package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByUsername(String username);
}
