package com.example.cv_thymeleaf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @Column
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

}
