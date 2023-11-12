package com.example.cv_thymeleaf.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManger(UserDetailsService detailsService) {
    System.out.println("In AuthenticationManager (UserDetailsService detailsService)");

    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(detailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(daoAuthenticationProvider);
  }

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
       .authorizeHttpRequests(authConfig -> {
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/h2-console/**"),
            AntPathRequestMatcher.antMatcher("/auth/**"),
            AntPathRequestMatcher.antMatcher("/logout"),
            AntPathRequestMatcher.antMatcher("/"),
            AntPathRequestMatcher.antMatcher("/skills/**"),
            AntPathRequestMatcher.antMatcher("/education/**")).permitAll();
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/experience/user/**")).hasAnyAuthority("ADMIN", "USER");
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/experience/admin/**")).hasAnyAuthority("ADMIN");
//         authConfig.requestMatchers(
//            AntPathRequestMatcher.antMatcher("/experience/user/**")).hasAnyRole("ADMIN", "USER");
//         authConfig.requestMatchers(
//            AntPathRequestMatcher.antMatcher("/experience/admin/**")).hasRole("ADMIN");
         authConfig.anyRequest().authenticated();
       })
       .csrf(AbstractHttpConfigurer::disable)
       .headers(AbstractHttpConfigurer::disable) // for working of h2-console
//       .formLogin(Customizer.withDefaults()) // Login with browser and form
       .formLogin(formLogin -> formLogin.loginPage("/auth/login").permitAll())
       .logout(logoutConfigurer -> {
         logoutConfigurer.logoutSuccessUrl("/");
       })
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

//  @Bean
//  JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//    return new JdbcUserDetailsManager(dataSource);
//  }

//  @Bean
//  DataSource dataSource() {
//    return new EmbeddedDatabaseBuilder()
//       .setType(EmbeddedDatabaseType.H2)
//       .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//       .build();
//  }

}
