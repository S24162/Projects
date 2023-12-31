package com.example.cv_thymeleaf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApplicationUser implements UserDetails {
  public ApplicationUser(String username, String password, Set<Role> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appuser_id")
  private Long appUserId;

  @Column(unique = true)
  private String username;

  private String password;

  //  , cascade = {CascadeType.MERGE, CascadeType.PERSIST}
//  , cascade = {CascadeType.ALL}
//  , cascade = {CascadeType.REFRESH}
  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinTable(name = "appuser_role_junction",
     joinColumns = {@JoinColumn(name = "appuser_id")},
     inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> authorities = new HashSet<>();

  //  (cascade = CascadeType.ALL)
  @OneToOne(cascade = CascadeType.ALL)
  private Person person;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
