package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  public ApplicationUser registerUser(String username, String password) {

    Role userRole = roleRepository.findByAuthority("USER").get();
    Set<Role> authorities = new HashSet<>();
    authorities.add(userRole);

    ApplicationUser newUser = ApplicationUser.getBlankUser();
    newUser.setUsername(username);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setAuthorities(authorities);

    System.out.println("### New user with name " + username + " and authorities USER is created");
//    return userRepository.save(new ApplicationUser(0L, username, encodedPassword, authorities, newExp));
    return userRepository.save(newUser);
  }


}
