package com.example.cv_thymeleaf.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role implements GrantedAuthority {

  public Role(String authority) {
    this.authority = authority;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long roleId;

  @Column(name = "authority")
  private String authority;



//  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
//  private Set<ApplicationUser> appUserSet;



  @Override
  public String getAuthority() {
    return this.authority;
  }
}
