package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.AppUserService;
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
@RequestMapping("/about")
public class AboutController {

  private final PersonService personService;

  @GetMapping("/unauth/{id}")
  public String getAboutUnauth(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal ApplicationUser user) {
//    try {
//      user.getAuthorities().stream().findFirst().get();
//    } catch (Exception e) {
//      user = appUserService.getBlankUser();
//    }

    Person person = personService.findById(id);

    model.addAttribute("user", user);
    model.addAttribute("person", person);
    return "about/aboutUnauth";
  }

  @GetMapping("/user")
  public String getAboutUser(@AuthenticationPrincipal ApplicationUser user,
                             Model model) {
    model.addAttribute("user", user);
    return "about/aboutUser";
  }

  @GetMapping("/editAbout")
  public String editExpGet(@AuthenticationPrincipal ApplicationUser user,
                           Model model) {
    model.addAttribute("user", user);
    return "about/editAbout";
  }

  @PostMapping("/editAbout")
  public RedirectView editExpPost(@AuthenticationPrincipal ApplicationUser user,
                                  @RequestParam String param) {
    user.getPerson().setAbout(param);
    personService.addPerson(user.getPerson());
    return new RedirectView("/about/user");
  }

}
