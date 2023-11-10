package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.LoginResponseDTO;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

  private UserRepository userRepository;

  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;

  private AuthenticationManager authenticationManager;

  private TokenService tokenService;

  public ApplicationUser registerUser(String username, String password) {

    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();

    authorities.add(userRole);

    return userRepository.save(new ApplicationUser(0L, username, encodedPassword, authorities));
  }

  public LoginResponseDTO loginUser(String username, String password) {
    Authentication auth = authenticationManager
       .authenticate(new UsernamePasswordAuthenticationToken(username, password));

    String token = tokenService.generateJwt(auth);

    return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
  }

}
