package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.AppUserService;
import com.example.cv_thymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

  private final AppUserService appUserService;
  private final PersonService personService;

  @GetMapping
  public String getList(@AuthenticationPrincipal UserDetails user,
                        Model model) {
//    try {
//      user.getAuthorities().stream().findFirst().get();
//    } catch (Exception e) {
//      user = appUserService.getBlankUser();
//    }
    List<Person> personList = personService.getAllPersons();

    model.addAttribute("user", user);
    model.addAttribute("personList", personList);

    return "index";
  }

}