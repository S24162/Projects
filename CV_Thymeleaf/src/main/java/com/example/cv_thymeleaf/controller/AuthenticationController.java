package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.LoginResponseDTO;
import com.example.cv_thymeleaf.model.RegistrationDTO;
import com.example.cv_thymeleaf.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

  @GetMapping("/login")
  public String getLogin() {
    return "auth/login";
  }

  private AuthenticationService authenticationService;

  @GetMapping("/register")
  public String getRegisterUser() {
    return "auth/register";
  }

  @PostMapping("/register")
  public String registerUser(@RequestParam String username, @RequestParam String password) {
    authenticationService.registerUser(username, password);
    return "auth/login";
  }

  @PostMapping("/login")
  public String postLogin(@RequestParam String username, @RequestParam String password) {
    authenticationService.loginUser(username, password);
    return "index";
  }

}
