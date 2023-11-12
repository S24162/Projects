package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {

  private final ExperienceService experienceService;

  @GetMapping("/user")
  public String getExpUser(@AuthenticationPrincipal UserDetails user,
                           Model model, Authentication authentication) {
    model.addAttribute("expList", experienceService.getExperienceList());
    model.addAttribute("auth", authentication);
    System.out.println(authentication.getName());

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return "experience/experienceUser";
  }

  @GetMapping("/admin")
  public String getExpAdmin(@AuthenticationPrincipal UserDetails user,
                            Model model, Authentication authentication) {
    model.addAttribute("expList", experienceService.getExperienceList());
    System.out.println(authentication.getName());

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return "experience/experienceAdmin";
  }

  @GetMapping("/addExp")
  public String addExp(@AuthenticationPrincipal UserDetails user,
                       Model model) {
    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return "experience/addExp";
  }

  @PostMapping("/addExp")
  public RedirectView addExp(@AuthenticationPrincipal UserDetails user,
                             Model model, Experience exp) {
    if (exp.getDateFrom() != null && exp.getDateTo() != null) {
      experienceService.addExperience(exp);
    } else {
      System.out.println("Wrong date (is null)");
      exp.setDateTo(LocalDate.now());
      exp.setDateFrom(LocalDate.now());
      experienceService.addExperience(exp);
    }

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return new RedirectView("/experience/admin");
  }

  @PostMapping("/deleteExp")
  public RedirectView deleteExp(@AuthenticationPrincipal UserDetails user,
                                Model model, @RequestParam(value = "param") Long id) {
    experienceService.deleteById(id);

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return new RedirectView("/experience/admin");
  }

  @GetMapping("/editExp")
  public String editExp(@AuthenticationPrincipal UserDetails user,
                        Model model, @RequestParam Long param) {
    model.addAttribute("exp", experienceService.getExperienceById(param));

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = ApplicationUser.getBlankUser();
    }

    model.addAttribute("user", user);
    return "experience/editExp";
  }

}
