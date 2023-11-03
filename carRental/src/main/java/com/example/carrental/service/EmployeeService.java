package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Employee;
import com.example.carrental.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  public List<Employee> getEmployeeList() {
    return employeeRepository.findAll();
  }

  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id).orElse(null);
  }

  public void editEmployee(Employee editEmployee) {
    employeeRepository.save(editEmployee);
  }

  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }
}
