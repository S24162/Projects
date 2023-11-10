package com.example.cv_thymeleaf.config;


import com.example.cv_thymeleaf.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final RSAKeyProperties keys = new RSAKeyProperties();

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManger(UserDetailsService detailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(detailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(daoAuthenticationProvider);
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtConverter;
  }

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
       .authorizeHttpRequests(authConfig -> {
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/h2-console/**"),
            AntPathRequestMatcher.antMatcher("/auth/**"),
            AntPathRequestMatcher.antMatcher("/logout"),
            AntPathRequestMatcher.antMatcher("/")).permitAll();
//         authConfig.requestMatchers(
//            AntPathRequestMatcher.antMatcher("/experience/user/**")).hasAnyAuthority("ADMIN", "USER");
//         authConfig.requestMatchers(
//            AntPathRequestMatcher.antMatcher("/experience/admin/**")).hasAnyAuthority("ADMIN");
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/experience/user/**")).hasAnyRole("ADMIN", "USER");
         authConfig.requestMatchers(
            AntPathRequestMatcher.antMatcher("/experience/admin/**")).hasRole("ADMIN");
       })
       .csrf(AbstractHttpConfigurer::disable)
       .headers(AbstractHttpConfigurer::disable)
       .formLogin(Customizer.withDefaults()) // Login with browser and form
       .formLogin(formLogin -> formLogin.loginPage("/auth/login").permitAll())
       .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/"))
//       .httpBasic(Customizer.withDefaults()); // Login with Insomnia and Basic Auth
       .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
       .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtAuthenticationConverter()))
       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
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
