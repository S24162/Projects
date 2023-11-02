package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepo extends JpaRepository<Experience, Long> {
}
