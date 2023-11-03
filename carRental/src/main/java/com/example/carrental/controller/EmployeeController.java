package com.example.carrental.controller;

import com.example.carrental.service.BranchService;
import com.example.carrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
  private final BranchService branchService;

  @GetMapping("/")
  public String getEmployee(Model model) {
    model.addAttribute("branchList", branchService.getBranchList());
    return "employees";
  }

}
