package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin // Połączenie innego serwera z tym api
@RequiredArgsConstructor
@RestController
public class PersonController {

  private final PersonRepo personRepo;

  @GetMapping("/api/persons")
  public List<Person> getPersons() {
    return personRepo.findAll();
  }


}
