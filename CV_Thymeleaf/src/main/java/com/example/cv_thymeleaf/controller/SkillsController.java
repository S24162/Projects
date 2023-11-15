package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Skill;
import com.example.cv_thymeleaf.services.AppUserService;
import com.example.cv_thymeleaf.services.PersonService;
import com.example.cv_thymeleaf.services.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillsController {

  private final PersonService personService;
  private final SkillsService skillsService;

  @GetMapping("/unauth/{id}")
  public String getSkillsUnauth(@PathVariable Long id, Model model,
                          @AuthenticationPrincipal ApplicationUser user) {

    Person person = personService.findById(id);

    model.addAttribute("user", user);
    model.addAttribute("person", person);
    return "skills/skillsUnauth";
  }

  @GetMapping("/user")
  public String getSkillsUser(@AuthenticationPrincipal ApplicationUser user,
                             Model model) {
    model.addAttribute("skillList", skillsService.getSortedListById(user));
    model.addAttribute("user", user);
    return "skills/skillsUser";
  }

  @GetMapping("/editSkill")
  public String getEditSkills(@RequestParam long param,
                              Model model) {
    model.addAttribute("skill", skillsService.getSkillById(param));
    return "/skills/editSkill";
  }

  @PostMapping("/editSkill")
  public RedirectView postEditSkill (@RequestParam long id, String text) {
    Skill skill = skillsService.getSkillById(id);
    skill.setText(text);
    skillsService.addSkill(skill);
    return new RedirectView("/skills/user");
  }

  @PostMapping("/deleteSkill")
  public RedirectView postDeleteSkill(@RequestParam long param) {
    skillsService.deleteEducationById(param);
    return new RedirectView("/skills/user");
  }

  @PostMapping("/addSkill")
  public RedirectView postAddEducation(@AuthenticationPrincipal ApplicationUser user,
                                   @RequestParam String text) {
    Skill newSkill = skillsService.getBlankSkill();
    newSkill.setText(text);
    newSkill.setPerson(user.getPerson());
    skillsService.addSkill(newSkill);
    user.getPerson().getSkillsSet().add(newSkill);
    return new RedirectView("/skills/user");
  }

}
