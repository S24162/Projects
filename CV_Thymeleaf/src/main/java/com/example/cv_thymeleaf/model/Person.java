package com.example.cv_thymeleaf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  private String about;

//  File file;
//  BufferedImage bufferedImage;
//  URL iconURL = new URL("");
//  // iconURL is null when not found
//  ImageIcon icon = new ImageIcon(iconURL);
//  Image i = icon.getImage();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  private Set<Experience> experienceSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  private Set<Education> educationSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
  private Set<Skill> skillsSet = new HashSet<>();

  private String interests;

  @OneToOne
  private ApplicationUser applicationUser;


}
