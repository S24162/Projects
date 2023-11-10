package com.example.cv_thymeleaf;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.repository.UserRepository;
import jakarta.persistence.SecondaryTable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
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
      if (roleRepository.findByAuthority("ADMIN").isPresent()) {
        System.out.println("already have role with name ADMIN");
        return;
      }
      Role adminRole = roleRepository.save(new Role("ADMIN"));
      roleRepository.save(new Role("USER"));

      Set<Role> roles = new HashSet<>();
      roles.add(adminRole);

      ApplicationUser admin = new ApplicationUser(1L, "admin", passwordEncoder.encode("admin"), roles);

      userRepository.save(admin);
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
