package com.example.cv_thymeleaf.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authConfig -> {
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/")).permitAll();
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/experience/user")).hasRole("USER");
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/experience/admin")).hasRole("ADMIN");
         authConfig.anyRequest().permitAll();
       })
       .csrf(AbstractHttpConfigurer::disable)
       .headers(AbstractHttpConfigurer::disable)
//       .formLogin(Customizer.withDefaults()) // Login with browser and form
       .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
       .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/"))
       .httpBasic(Customizer.withDefaults()); // Login with Insomnia and Basic Auth
    return http.build();
  }

  /*
  @Bean
  UserDetailsService userDetailsService() {
    var admin = User.builder()
       .username("admin")
       .password(passwordEncoder().encode("admin"))
       .roles("USER", "ADMIN")
       .build();
    UserDetails user = User.builder()
       .username("user")
       .password(passwordEncoder().encode("user"))
       .roles("USER")
       .build();
//    return new InMemoryUserDetailsManager(admin, user); - It works the same.
    return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
  }
  */

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//-------------

  @Bean
  JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
       .setType(EmbeddedDatabaseType.H2)
       .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
       .build();
  }

  @Bean
  CommandLineRunner commandLineRunner(JdbcUserDetailsManager userDetailsManager, DataSource dataSource) {
    return args -> {
      var admin = User.builder()
         .username("admin")
         .password(passwordEncoder().encode("admin"))
         .roles("USER", "ADMIN")
         .build();
      UserDetails user = User.builder()
         .username("user")
         .password(passwordEncoder().encode("user"))
         .roles("USER")
         .build();
      JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
      users.createUser(user);
      users.createUser(admin);
    };
  }

}
