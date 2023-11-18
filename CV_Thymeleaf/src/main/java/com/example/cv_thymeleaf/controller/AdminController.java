package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final AppUserService appUserService;

  @PostMapping("/delete/{id}")
  public RedirectView postDeleteUser(@PathVariable Long id) {

    appUserService.deleteAppUser(id);
    return new RedirectView("/");
  }
}
