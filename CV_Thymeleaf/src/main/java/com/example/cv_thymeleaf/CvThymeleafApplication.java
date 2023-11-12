package com.example.cv_thymeleaf;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CvThymeleafApplication {

  public static void main(String[] args) {
    SpringApplication.run(CvThymeleafApplication.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      if (roleRepository.findByAuthority("ADMIN").isPresent() && roleRepository.findByAuthority("USER").isPresent()) {
        System.out.println("Presented roles: ADMIN, USER");

        Role adminRole = roleRepository.findByAuthority("ADMIN").get();
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        adminRoles.add(adminRole);
        userRoles.add(userRole);

        Set<Experience> newExp = new HashSet<>();

        ApplicationUser newUser = new ApplicationUser(1L, "admin", passwordEncoder.encode("admin"), adminRoles, newExp);
        userRepository.save(newUser);
        newUser = new ApplicationUser(2L, "user", passwordEncoder.encode("user"), userRoles, newExp);
        userRepository.save(newUser);
      } else {
        System.out.println("Roles ADMIN or USER is not found. Roles is not defined.");
      }
    };
  }

//  @Bean
//  CommandLineRunner run(JdbcUserDetailsManager userDetailsManager, DataSource dataSource) {
//    return args -> {
//      var admin = User.builder()
//         .username("admin")
//         .password(passwordEncoder().encode("admin"))
//         .roles("USER", "ADMIN")
//         .build();
//      UserDetails user = User.builder()
//         .username("user")
//         .password(passwordEncoder().encode("user"))
//         .roles("USER")
//         .build();
//      JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//      users.createUser(user);
//      users.createUser(admin);
//    };
//  }

}
