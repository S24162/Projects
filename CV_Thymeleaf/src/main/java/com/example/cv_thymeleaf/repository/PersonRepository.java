package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
