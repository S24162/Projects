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
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @Column(name = "surname", length = 20, nullable = false)
  private String surname;

  @Column(name = "job_position", length = 20)
  private String jobPosition;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "Employee_Branch"
     , foreignKeyDefinition = """
     FOREIGN KEY Employee_Branch (branch_id)
             REFERENCES Branch (id)
             ON DELETE SET NULL
             ON UPDATE CASCADE"""))
  private Branch branch;

  @OneToMany(mappedBy = "employee")
  private Set<CarTake> carTakeSet;

  @OneToMany(mappedBy = "employee")
  private Set<CarReturn> carReturnSet;

}
