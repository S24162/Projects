package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
