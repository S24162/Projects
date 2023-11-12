package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

//  private PasswordEncoder passwordEncoder;

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("In the UserDetailService (UserService); method 'loadUserByUsername' ");
//    if (!username.equals("Eugenio")) throw new UsernameNotFoundException("Not a user");
//
//    Set<Role> roles = new HashSet<>();
//    roles.add(new Role(1, "USER"));
//
//    return new ApplicationUser(1L, "Eugenio", passwordEncoder.encode("user"),roles);

    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
  }
}
