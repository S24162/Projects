package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Car_take")
public class CarTake {

  @Id
  @OneToOne(optional = false)
  @JoinColumn(foreignKey = @ForeignKey(name = "Car_take_Reservation"
     , foreignKeyDefinition = """
     FOREIGN KEY Car_take_Reservation (reservation_Id)
             REFERENCES Reservation (id)
             ON DELETE CASCADE
             ON UPDATE CASCADE"""))
  private Reservation reservation;

  @Column(name = "date", nullable = false, columnDefinition = "datetime")
  private LocalDateTime date;

  @Column(name = "comments", length = 500)
  private String comments;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "Car_take_Employee"
     , foreignKeyDefinition = """
     FOREIGN KEY Car_take_Employee (employee_Id)
             REFERENCES Employee (id)
             ON DELETE SET NULL
             ON UPDATE SET NULL"""))
  private Employee employee;
}
