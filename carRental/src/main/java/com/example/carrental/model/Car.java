package com.example.carrental.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {

  @Id
  @Column(name = "vin_number", length = 20)
  private String vinNumber;

  @Column(name = "brand", length = 20, nullable = false)
  private String brand;

  @Column(name = "model", length = 20, nullable = false)
  private String model;

  @Column(name = "body_type", length = 20, nullable = false)
  private String bodyType;

  @Column(name = "color", length = 20, nullable = false)
  private String color;

  @Column(name = "year", precision = 4, nullable = false)
  private BigDecimal year;

  @Column(name = "mileage_km", precision = 8, scale = 1)
  private BigDecimal mileageKm;

  @Column(name = "status", length = 20, nullable = false)
  private String status;

  @Column(name = "price", precision = 6, scale = 2, nullable = false)
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "Car_Branch"
     , foreignKeyDefinition = """
     FOREIGN KEY Car_Branch (branch_id)
             REFERENCES Branch (id)        ON DELETE SET NULL
             ON UPDATE CASCADE"""))
  private Branch branch;

  @OneToMany(mappedBy = "car")
  private Set<Reservation> reservationSet;
}
