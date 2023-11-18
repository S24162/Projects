package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

  private final PersonService personService;

  @GetMapping
  public String getList(@AuthenticationPrincipal ApplicationUser user,
                        Model model) {

    List<Person> personList = personService.getAllPersons();

    if (user != null) {
      model.addAttribute("person", user.getPerson());
    }
    model.addAttribute("user", user);
    model.addAttribute("personList", personList);

    if (user != null) {
      model.addAttribute("role", user.getAuthorities().stream().findFirst().get().getAuthority());
    }
    return "index";
  }

}