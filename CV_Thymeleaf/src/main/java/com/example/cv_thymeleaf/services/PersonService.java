package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;

  public Person addPerson(Person person) {
    return personRepository.save(person);
  }

  public Person getBlankPerson() {

    Person newPerson = new Person();
    newPerson.setBrand("Some brand");
    newPerson.setAbout("Something about");
    newPerson.setInterests("Some interests are here.");
    newPerson.setName("Some name");

    return newPerson;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public Person findById(long id) {
    return personRepository.findById(id).orElse(getBlankPerson());
  }
}
