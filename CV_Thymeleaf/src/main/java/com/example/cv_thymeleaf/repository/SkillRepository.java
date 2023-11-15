package com.example.cv_thymeleaf.repository;

import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
  public List<Skill> findAllByPersonOrderByIdAsc(Person person);
}
