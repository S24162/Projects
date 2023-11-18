package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/registration")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  @GetMapping("/login")
  public String getLogin(@NotNull Model model) {
    model.addAttribute("condition", false);
    return "/auth/login";
  }

  @PostMapping("/login")
  public String postLogin(@RequestParam String username, @RequestParam String password) {
//    authenticationService.loginUser(username, password);
    return "/index";
  }

  @GetMapping("/register")
  public String getRegisterUser() {
    return "/auth/register";
  }

  @PostMapping("/register")
  public String postRegisterUser(@RequestParam String username,
                                 @RequestParam String password,
                                 @NotNull Model model) {
    ApplicationUser newUser = authenticationService.registerUser(username, password);
    model.addAttribute("newUser", newUser);
    model.addAttribute("condition", true);
    return "/auth/login";
  }

}
