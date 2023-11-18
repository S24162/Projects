package com.example.cv_thymeleaf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "person_id")
  private Long id;

  private String name;

  private String brand;

  @Column(length = 2048)
  private String about;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Experience> experienceSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Education> educationSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Skill> skillsSet = new HashSet<>();

  private String interests;

  @OneToOne
  private ApplicationUser applicationUser;


}
