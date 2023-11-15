package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
  public List<Experience> findAllByPersonOrderByDateFromDesc(Person person);
}
