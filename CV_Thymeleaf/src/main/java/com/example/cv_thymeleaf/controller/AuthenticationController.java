package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  @GetMapping("/login")
  public String getLogin(Model model) {
    model.addAttribute("condition", false);
    return "auth/login";
  }

  @GetMapping("/register")
  public String getRegisterUser() {
    return "auth/register";
  }

  @PostMapping("/register")
  public String registerUser(@RequestParam String username,
                             @RequestParam String password,
                             @AuthenticationPrincipal UserDetails user,
                             Model model) {
    ApplicationUser newUser = authenticationService.registerUser(username, password);
    model.addAttribute("newUser", newUser);
    model.addAttribute("condition", true);
    return "auth/login";
  }

  @PostMapping("/login")
  public String postLogin(@RequestParam String username, @RequestParam String password) {
//    authenticationService.loginUser(username, password);
    return "index";
  }

}
