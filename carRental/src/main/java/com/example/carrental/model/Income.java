package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Income {

  @Id
  private LocalDate yearAndMonth;

  @Column(name = "total", nullable = false, precision = 10, scale = 2)
  private BigDecimal total;
}
