package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @Column(name = "surname", length = 20, nullable = false)
  private String surname;

  @Column(name = "email", length = 30)
  private String email;

  @Column(name = "address", length = 50, nullable = false)
  private String address;

  @OneToMany(mappedBy = "customer")
  private Set<Reservation> reservationSet;


}
