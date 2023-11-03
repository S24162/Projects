package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date_from", nullable = false, columnDefinition = "datetime")
  private LocalDateTime dateFrom;

  @Column(name = "date_to", nullable = false, columnDefinition = "datetime")
  private LocalDateTime dateTo;

  @Column(name = "total_price", precision = 8, scale = 2, nullable = false)
  private BigDecimal totalPrice;

  @ManyToOne(optional = false)
  @JoinColumn(name = "branch_id_from",foreignKey=@ForeignKey(name="Reservation_Branch_from"
  , foreignKeyDefinition = """
     FOREIGN KEY Reservation_Branch_from (branch_id_from)
             REFERENCES Branch (id)
             ON UPDATE CASCADE"""))
  private Branch branchFrom;

  @ManyToOne(optional = false)
  @JoinColumn(name = "branch_id_to",foreignKey=@ForeignKey(name="Reservation_Branch_to"
  , foreignKeyDefinition = """
     FOREIGN KEY Reservation_Branch_to (branch_id_to)
             REFERENCES Branch (id)
             ON UPDATE CASCADE"""))
  private Branch branchTo;

  @ManyToOne(optional = false)
  @JoinColumn(foreignKey=@ForeignKey(name="Reservation_Customer"
  , foreignKeyDefinition = """
     FOREIGN KEY Reservation_Customer (Customer_id)
             REFERENCES Customer (id)
             ON DELETE CASCADE
             ON UPDATE CASCADE"""))
  private Customer customer;

  @ManyToOne(optional = false)
  @JoinColumn(foreignKey=@ForeignKey(name="Reservation_Car"
  , foreignKeyDefinition = """
     FOREIGN KEY Reservation_Car (Car_vin_number)
             REFERENCES Car (vin_number)
             ON UPDATE CASCADE"""))
  private Car car;
}
