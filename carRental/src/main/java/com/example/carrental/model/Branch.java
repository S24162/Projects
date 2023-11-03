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
public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 30, nullable = false)
  private String name;

  @Column(name = "address", length = 50, nullable = false)
  private String address;

  @OneToMany(mappedBy = "branch")
  private Set<Car> carSet;

  @OneToMany(mappedBy = "branch")
  private Set<Employee> employeeSet;

  @OneToMany(mappedBy = "branchFrom")
  private Set<Reservation> reservationSetFrom;

  @OneToMany(mappedBy = "branchTo")
  private Set<Reservation> reservationSetTo;
}

