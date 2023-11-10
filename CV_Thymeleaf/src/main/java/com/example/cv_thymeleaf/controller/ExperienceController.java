package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.services.ExperienceService;
import com.example.cv_thymeleaf.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
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
  public String getExpUser(Model model, Authentication authentication) {
    model.addAttribute("expList", experienceService.getExperienceList());
    model.addAttribute("auth", authentication);
    System.out.println(authentication.getName());
    return "experience/experienceUser";
  }

  @GetMapping("/admin")
  public String getExpAdmin(Model model, Authentication authentication) {
    model.addAttribute("expList", experienceService.getExperienceList());
    System.out.println(authentication.getName());
    return "experience/experienceAdmin";
  }

  @GetMapping("/addExp")
  public String addExp() {
    return "experience/addExp";
  }

  @PostMapping("/addExp")
  public RedirectView addExp(Experience exp) {
    if (exp.getDateFrom() != null && exp.getDateTo() != null) {
      experienceService.addExperience(exp);
    } else {
      System.out.println("Wrong date (is null)");
      exp.setDateTo(LocalDate.now());
      exp.setDateFrom(LocalDate.now());
      experienceService.addExperience(exp);
    }
    return new RedirectView("/experience/admin");
  }

  @PostMapping("/deleteExp")
  public RedirectView deleteExp(@RequestParam(value = "param") Long id) {
    experienceService.deleteById(id);
    return new RedirectView("/experience/admin");
  }

  @GetMapping("/editExp")
  public String editExp(@RequestParam Long param, Model model) {
    model.addAttribute("exp", experienceService.getExperienceById(param));
    return "experience/editExp";
  }

}
