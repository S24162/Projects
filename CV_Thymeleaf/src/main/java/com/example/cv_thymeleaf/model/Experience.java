package com.example.cv_thymeleaf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Experience {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "position")
  private String position;

  @Column(name = "organization")
  private String organization;

  @Column(name = "description",length = 2048)
  private String description;

  @Column(name = "date_from")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateFrom;

  @Column(name = "date_to")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateTo;

  @ManyToOne
  private Person person;

}
