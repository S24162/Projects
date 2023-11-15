package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

  private AppUserService userService;
  private RoleRepository roleRepository;
  private PersonService personService;
  private EducationService educationService;
  private PasswordEncoder passwordEncoder;

  public ApplicationUser registerUser(String username, String password) {

    ApplicationUser newUser = userService.getBlankUser();

    Role userRole = roleRepository.findByAuthority("USER").get();
    Set<Role> authorities = new HashSet<>();

    authorities.add(userRole);
    newUser.setAuthorities(authorities);
    newUser.setUsername(username);
    newUser.setPassword(passwordEncoder.encode(password));

    userService.addAppUser(newUser);

    Person newPerson = personService.getBlankPerson();
    personService.addPerson(newPerson);
    newPerson.setApplicationUser(newUser);
    newUser.setPerson(newPerson);

    Education newEducation = educationService.getBlankEducation();
    educationService.addEducation(newEducation);
    newEducation.setPerson(newPerson);
    newPerson.getEducationSet().add(newEducation);



    System.out.println("### New user with name " + username + " and authorities USER is created");
    return userService.addAppUser(newUser);
  }


}
