package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

//  private PasswordEncoder passwordEncoder;

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("In the user detail service");
//    if (!username.equals("Eugenio")) throw new UsernameNotFoundException("Not a user");
//
//    Set<Role> roles = new HashSet<>();
//    roles.add(new Role(1, "USER"));
//
//
//    return new ApplicationUser(1L, "Eugenio", passwordEncoder.encode("user"),roles);

    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
  }
}
