package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

  private AppUserService appUserService;
  private RoleRepository roleRepository;
  private PersonService personService;

  public ApplicationUser registerUser(String username, String password) {
    Set<Role> userRoleSet = new HashSet<>();
    if (Objects.equals(username, "admin")) {
      userRoleSet.add(roleRepository.findByAuthority("ADMIN").get());
    } else {
      userRoleSet.add(roleRepository.findByAuthority("USER").get());
    }

    ApplicationUser newUser = appUserService.getBlankUser(username, password);

    newUser.setAuthorities(userRoleSet);
    if (!Objects.equals(username, "admin")) {
      Person newPerson = personService.registerNewPersonWithAppUser(newUser);
      newUser.setPerson(newPerson);
    }

    System.out.println("## New user with username '" + username + "' is setup by AuthenticationService.registerUser");
    return appUserService.addAppUser(newUser);
  }


}
