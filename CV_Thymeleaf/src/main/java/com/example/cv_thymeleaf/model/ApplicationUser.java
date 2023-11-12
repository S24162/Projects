package com.example.cv_thymeleaf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_user")
public class ApplicationUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(unique = true)
  private String username;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role_junction",
     joinColumns = {@JoinColumn(name = "user_id")},
     inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> authorities;

  @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
  private Set<Experience> experience;


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

  public static ApplicationUser getBlankUser() {

    Role blankRole = new Role();
    blankRole.setAuthority("blank Authority");

    Set<Role> roles = new HashSet<>();
    roles.add(blankRole);

    ApplicationUser blankUser = new ApplicationUser();

    blankUser.setUsername("blank user");
    blankUser.setAuthorities(roles);
    return blankUser;
  }

}
