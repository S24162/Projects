package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Role;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()

public class EducationController {

  @GetMapping("/education")
  public ModelAndView getHome(@AuthenticationPrincipal ApplicationUser user, ModelMap map, Role role) {
    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    ModelAndView modelAndView = new ModelAndView("education");
    modelAndView.addObject("user", user);
    map.put("role", role.getAuthority());

    return modelAndView;
  }
}
