package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Car_return")
public class CarReturn {

  @Id
  @OneToOne(optional = false)
  @JoinColumn(foreignKey = @ForeignKey(name = "Car_return_Reservation"
     , foreignKeyDefinition = """
     FOREIGN KEY Car_return_Reservation (reservation_Id)
             REFERENCES Reservation (id)
             ON DELETE CASCADE
             ON UPDATE CASCADE"""))
  private Reservation reservation;

  @Column(name = "date", nullable = false, columnDefinition = "datetime")
  private LocalDateTime date;

  @Column(name = "comments", length = 500)
  private String comments;

  @Column(name = "additional_fees", precision = 10, scale = 2, nullable = false)
  private BigDecimal additionalFees;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "Car_return_Employee"
     , foreignKeyDefinition = """
     FOREIGN KEY Car_return_Employee (employee_Id)
             REFERENCES Employee (id)
             ON DELETE SET NULL
             ON UPDATE SET NULL"""))
  private Employee employee;
}
