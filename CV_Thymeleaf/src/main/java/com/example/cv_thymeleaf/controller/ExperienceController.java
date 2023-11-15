package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.AppUserService;
import com.example.cv_thymeleaf.services.ExperienceService;
import com.example.cv_thymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {

  private final ExperienceService experienceService;
  private final AppUserService appUserService;
  private final PersonService personService;

  @GetMapping("/unauth/{id}")
  public String getExpUnauth(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal UserDetails user) {
    model.addAttribute("expList", experienceService.getExperienceList());
    Person person = personService.findById(id);
    model.addAttribute("person", person);
    model.addAttribute("user", user);
//    model.addAttribute("auth", authentication);
    return "experience/experienceUnauth";
  }

  @GetMapping("/user")
  public String getExpUser(@AuthenticationPrincipal ApplicationUser user,
                            Model model, Authentication authentication) {
    model.addAttribute("expList", experienceService.getExperienceList());
    System.out.println(authentication.getName());

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = appUserService.getBlankUser();
    }

    model.addAttribute("user", user);

    List<Experience> expSortedList = experienceService.getSortedListByDateFrom(user);
    model.addAttribute("expList", expSortedList );
    return "experience/experienceUser";
  }

//  @GetMapping("/addExp")
//  public String addExp(@AuthenticationPrincipal ApplicationUser user,
//                       Model model) {
//    try {
//      user.getAuthorities().stream().findFirst().get();
//    } catch (Exception e) {
//      user = appUserService.getBlankUser();
//    }
//
//    model.addAttribute("user", user);
//
//    return "experience/addExp";
//  }

  @PostMapping("/addExp")
  public RedirectView addExp(@AuthenticationPrincipal ApplicationUser user,
                             Model model, Experience exp) {
    if (exp.getDateFrom() != null && exp.getDateTo() != null) {
      exp.setPerson(user.getPerson());
      experienceService.addExperience(exp);
    } else {
      System.out.println("Wrong date (is null)");
      exp.setDateTo(LocalDate.now());
      exp.setDateFrom(LocalDate.now());
      exp.setPerson(user.getPerson());
      user.getPerson().getExperienceSet().add(exp);
      experienceService.addExperience(exp);
    }

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = appUserService.getBlankUser();
    }

    model.addAttribute("user", user);
    return new RedirectView("/experience/user");
  }

  @PostMapping("/deleteExp")
  public RedirectView deleteExp(@AuthenticationPrincipal ApplicationUser user,
                                Model model, @RequestParam(value = "param") Long id) {
    experienceService.deleteById(id);

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = appUserService.getBlankUser();
    }

    model.addAttribute("user", user);
    return new RedirectView("/experience/user");
  }

  @GetMapping("/editExp")
  public String editExp(@AuthenticationPrincipal ApplicationUser user,
                        Model model, @RequestParam Long param) {
    model.addAttribute("exp", experienceService.getExperienceById(param));

    try {
      user.getAuthorities().stream().findFirst().get();
    } catch (Exception e) {
      user = appUserService.getBlankUser();
    }

    model.addAttribute("user", user);
    return "experience/editExp";
  }

}
