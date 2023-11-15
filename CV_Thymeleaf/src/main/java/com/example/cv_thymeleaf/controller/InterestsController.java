package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/interests")
public class InterestsController {

  public final PersonService personService;

  @GetMapping("/unauth/{id}")
  public String getInterests(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal UserDetails user){

    Person person = personService.findById(id);

    model.addAttribute("user", user);
    model.addAttribute("person", person);
    return "/interests/interestsUnauth";
  }

  @GetMapping("/user")
  public String getInterestsUser(@AuthenticationPrincipal ApplicationUser user,
                             Model model) {
    model.addAttribute("user", user);
    return "/interests/interestsUser";
  }

  @GetMapping("/editInterests")
  public String editInterestsUser(@AuthenticationPrincipal ApplicationUser user,
                           Model model) {
    model.addAttribute("user", user);
    return "interests/editInterests";
  }

  @PostMapping("/editInterests")
  public RedirectView editExpPost(@AuthenticationPrincipal ApplicationUser user,
                                  @RequestParam String param) {
    user.getPerson().setInterests(param);
    return new RedirectView("/interests/user");
  }

}
