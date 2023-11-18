package com.example.cv_thymeleaf.controller;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.model.Skill;
import com.example.cv_thymeleaf.services.EducationService;
import com.example.cv_thymeleaf.services.ExperienceService;
import com.example.cv_thymeleaf.services.PersonService;
import com.example.cv_thymeleaf.services.SkillsService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final PersonService personService;
  private final ExperienceService experienceService;
  private final EducationService educationService;
  private final SkillsService skillsService;


  //  About
  @GetMapping("/about")
  public String getAbout(@AuthenticationPrincipal ApplicationUser user,
                         @NotNull Model model) {

    model.addAttribute("user", user);
    model.addAttribute("person", user.getPerson());
    return "/about/aboutUser";
  }

  @PostMapping("/about/editAbout")
  public RedirectView editAbout(@AuthenticationPrincipal @NotNull ApplicationUser user,
                                @RequestParam String name, String brand, String about,
                                RedirectAttributes redirectAttributes
  ) {
    user.getPerson().setBrand(brand);
    user.getPerson().setName(name);
    user.getPerson().setAbout(about);
    personService.addPerson(user.getPerson());
    redirectAttributes.addAttribute("id", user.getPerson().getId());
    return new RedirectView("/unauth/about/{id}");
  }


  //  Experience
  @GetMapping("/experience")
  public String getExp(@AuthenticationPrincipal ApplicationUser user,
                       @NotNull Model model) {

    model.addAttribute("user", user);
    model.addAttribute("person", user.getPerson());
    model.addAttribute("expList", experienceService.getSortedListByDateFrom(user.getPerson()));
    return "/experience/experienceUser";
  }

  @GetMapping("/experience/editExp")
  public String getEditExp(@AuthenticationPrincipal ApplicationUser user,
                           @NotNull Model model, @RequestParam Long param) {

    model.addAttribute("exp", experienceService.getExperienceById(param));
    model.addAttribute("user", user);
    return "/experience/editExp";
  }

  @PostMapping("/experience/deleteExp")
  public RedirectView postDeleteExp(@AuthenticationPrincipal ApplicationUser user,
                                    @NotNull Model model, @RequestParam(value = "param") Long id) {

    experienceService.deleteById(id);
    model.addAttribute("user", user);
    return new RedirectView("/user/experience");
  }

  @PostMapping("/experience/addExp")
  public RedirectView postAddExp(@AuthenticationPrincipal ApplicationUser user,
                                 Model model, @NotNull Experience exp) {

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

    model.addAttribute("user", user);
    return new RedirectView("/user/experience");
  }


  //  Education
  @GetMapping("/education")
  public String getEducation(@AuthenticationPrincipal ApplicationUser user,
                             @NotNull Model model) {

    model.addAttribute("user", user);
    model.addAttribute("person", user.getPerson());
    model.addAttribute("eduList", educationService.getSortedListById(user.getPerson()));
    return "/education/educationUser";
  }

  @GetMapping("/education/editEdu")
  public String getEditEducation(@RequestParam long param, @NotNull Model model) {

    model.addAttribute("education", educationService.getEducationById(param));
    return "/education/editEducation";
  }

  @PostMapping("/education/editEdu")
  public RedirectView postEditEducation(@RequestParam long id, String text) {

    Education education = educationService.getEducationById(id);
    education.setText(text);
    educationService.addEducation(education);
    return new RedirectView("/user/education");
  }

  @PostMapping("/education/deleteEdu")
  public RedirectView postDeleteEducation(@RequestParam long param) {

    educationService.deleteEducationById(param);
    return new RedirectView("/user/education");
  }

  @PostMapping("/education/addEdu")
  public RedirectView postAddEducation(@AuthenticationPrincipal @NotNull ApplicationUser user,
                                       @RequestParam String text) {

    Education newEdu = educationService.getBlankEducation();
    newEdu.setText(text);
    newEdu.setPerson(user.getPerson());
    educationService.addEducation(newEdu);
    user.getPerson().getEducationSet().add(newEdu);
    return new RedirectView("/user/education");
  }

  //  Skills
  @GetMapping("/skills")
  public String getSkills(@AuthenticationPrincipal ApplicationUser user,
                          @NotNull Model model) {

    model.addAttribute("user", user);
    model.addAttribute("person", user.getPerson());
    model.addAttribute("skillList", skillsService.getSortedListById(user.getPerson()));
    return "/skills/skillsUser";
  }

  @GetMapping("/skills/editSkill")
  public String getEditSkills(@RequestParam long param,
                              @NotNull Model model) {

    model.addAttribute("skill", skillsService.getSkillById(param));
    return "/skills/editSkill";
  }

  @PostMapping("/skills/editSkill")
  public RedirectView postEditSkill(@RequestParam long id, String text) {

    Skill skill = skillsService.getSkillById(id);
    skill.setText(text);
    skillsService.addSkill(skill);
    return new RedirectView("/user/skills");
  }

  @PostMapping("/skills/deleteSkill")
  public RedirectView postDeleteSkill(@RequestParam long param) {
    skillsService.deleteEducationById(param);
    return new RedirectView("/user/skills");
  }

  @PostMapping("/skills/addSkill")
  public RedirectView postAddSkill(@AuthenticationPrincipal @NotNull ApplicationUser user,
                                   @RequestParam String text) {
    Skill newSkill = skillsService.getBlankSkill();
    newSkill.setText(text);
    newSkill.setPerson(user.getPerson());
    skillsService.addSkill(newSkill);
    user.getPerson().getSkillsSet().add(newSkill);
    return new RedirectView("/user/skills");
  }

  //  Interests
  @GetMapping("/interests")
  public String getInterests(@AuthenticationPrincipal ApplicationUser user,
                             @NotNull Model model) {

    model.addAttribute("user", user);
    if (user.getPerson() != null) {
      model.addAttribute("person", user.getPerson());
    }
    return "/interests/interestsUser";
  }

  @GetMapping("/interests/editInterests")
  public String getEditInterests(@AuthenticationPrincipal ApplicationUser user,
                                 @NotNull Model model) {

    model.addAttribute("user", user);
    return "/interests/editInterests";
  }

  @PostMapping("/interests/editInterests")
  public RedirectView postEditInterests(@AuthenticationPrincipal @NotNull ApplicationUser user,
                                        @RequestParam String param,
                                        @NotNull RedirectAttributes redirectAttributes) {

    user.getPerson().setInterests(param);
    personService.addPerson(user.getPerson());
    redirectAttributes.addAttribute("id", user.getPerson().getId());
    return new RedirectView("/unauth/interests/{id}");
  }

}
