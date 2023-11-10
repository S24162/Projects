package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<ApplicationUser, Long> {
}
