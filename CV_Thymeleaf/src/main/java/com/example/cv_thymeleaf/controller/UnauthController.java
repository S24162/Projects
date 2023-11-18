package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.services.EducationService;
import com.example.cv_thymeleaf.services.ExperienceService;
import com.example.cv_thymeleaf.services.PersonService;
import com.example.cv_thymeleaf.services.SkillsService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/unauth")
public class UnauthController {

  private final PersonService personService;
  private final ExperienceService experienceService;
  private final EducationService educationService;
  private final SkillsService skillsService;

  //  About
  @GetMapping("/about/{id}")
  public String getAboutUnauth(@AuthenticationPrincipal ApplicationUser user,
                               @PathVariable Long id, @NotNull Model model) {

    Person person = personService.findById(id);

    model.addAttribute("user", user);
    model.addAttribute("person", person);
    return "/about/aboutUnauth";
  }

  //  Experience
  @GetMapping("/experience/{id}")
  public String getExpUnauth(@AuthenticationPrincipal ApplicationUser user,
                             @PathVariable Long id, @NotNull Model model) {

    Person person = personService.findById(id);
    model.addAttribute("user", user);
    model.addAttribute("person", person);
    model.addAttribute("expList", experienceService.getSortedListByDateFrom(person));
    return "/experience/experienceUnauth";
  }


  //  Education
  @GetMapping("/education/{id}")
  public ModelAndView getEducationUnauth(@AuthenticationPrincipal ApplicationUser user,
                                         @PathVariable Long id, @NotNull ModelMap map) {

    ModelAndView modelAndView = new ModelAndView("/education/educationUnauth");
    modelAndView.addObject("user", user);

    map.put("role", null);
    if (user != null) {
      map.put("role", user.getAuthorities().stream().findFirst().get().getAuthority());
    }

    Person person = personService.findById(id);
    modelAndView.addObject("user", user);
    modelAndView.addObject("person", person);
    modelAndView.addObject("eduList", educationService.getSortedListById(person));
    return modelAndView;
  }

  //  Skills
  @GetMapping("/skills/{id}")
  public String getSkillsUnauth(@AuthenticationPrincipal ApplicationUser user,
                                @PathVariable Long id, @NotNull Model model) {

    Person person = personService.findById(id);

    model.addAttribute("user", user);
    model.addAttribute("person", person);
    model.addAttribute("skillList", skillsService.getSortedListById(person));
    return "/skills/skillsUnauth";
  }

  //  Interests
  @GetMapping("/interests/{id}")
  public String getInterests(@AuthenticationPrincipal UserDetails user,
                             @PathVariable Long id, @NotNull Model model) {

    Person person = personService.findById(id);
    model.addAttribute("user", user);
    model.addAttribute("person", person);
    return "/interests/interestsUnauth";
  }

}
