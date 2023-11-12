package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class InterestsController {

  @GetMapping("interests")
  public String getHome(@AuthenticationPrincipal UserDetails user,
                        Model model){

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return "interests";
  }
}
