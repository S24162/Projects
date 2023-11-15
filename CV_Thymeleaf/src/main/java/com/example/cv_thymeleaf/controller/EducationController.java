package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.services.AppUserService;
import com.example.cv_thymeleaf.services.EducationService;
import com.example.cv_thymeleaf.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
@RequestMapping("/education")

public class EducationController {

  private final PersonService personService;
  private final EducationService educationService;

  @GetMapping("/unauth/{id}")
  public ModelAndView getEducationUnauth(@PathVariable Long id, ModelMap map,
                                         @AuthenticationPrincipal ApplicationUser user) {
    ModelAndView modelAndView = new ModelAndView("education/educationUnauth");

    modelAndView.addObject("user", user);

    map.put("role", null);
    if (user != null) {
      map.put("role", user.getAuthorities().stream().findFirst().get().getAuthority());
    }

    Person person = personService.findById(id);
    System.out.println("Person ID : " + person.getId());

    modelAndView.addObject("user", user);
    modelAndView.addObject("person", person);

    return modelAndView;
  }

  @GetMapping("/user")
  public String getEducationUser(@AuthenticationPrincipal ApplicationUser user,
                                 Model model) {
    model.addAttribute("user", user);
    model.addAttribute("eduList", educationService.getSortedListById(user));
    return "education/educationUser";
  }

  @GetMapping("/editEdu")
  public String getEditEducation(@RequestParam long param,
                              Model model) {
    model.addAttribute("education", educationService.getEducationById(param));
    return "/education/editEducation";
  }

  @PostMapping("/editEdu")
  public RedirectView postEditEducation (@RequestParam long id, String text) {
    Education education= educationService.getEducationById(id);
    education.setText(text);
    educationService.addEducation(education);
    return new RedirectView("/education/user");
  }

  @PostMapping("/deleteEdu")
  public RedirectView postDeleteEducation(@RequestParam long param) {
    educationService.deleteEducationById(param);
    return new RedirectView("/education/user");
  }

  @PostMapping("/addEdu")
  public RedirectView postAddEducation(@AuthenticationPrincipal ApplicationUser user,
                                   @RequestParam String text) {
    Education newEdu = educationService.getBlankEducation();
    newEdu.setText(text);
    newEdu.setPerson(user.getPerson());
    educationService.addEducation(newEdu);
    user.getPerson().getEducationSet().add(newEdu);
    return new RedirectView("/education/user");
  }

}
