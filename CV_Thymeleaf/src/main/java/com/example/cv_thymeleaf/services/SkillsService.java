package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Interest;
import com.example.cv_thymeleaf.model.Skill;
import com.example.cv_thymeleaf.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsService {

  private final SkillRepository skillRepository;

  public Skill getBlankSkill() {
    Skill newBlankSkill = new Skill();
    newBlankSkill.setText("Here will be description of your education");
    return newBlankSkill;
  }

  public Skill addSkill(Skill skill) {
    return skillRepository.save(skill);
  }

  public List<Skill> getSkillList() {
    return skillRepository.findAll();
  }

  public Skill getSkillById(long id) {
    if (skillRepository.findById(id).isPresent()) {
      return skillRepository.findById(id).get();
    } else return null;
  }

  public List<Skill> getSortedListById(ApplicationUser appUser) {
    return skillRepository.findAllByPersonOrderByIdAsc(appUser.getPerson());
  }

  public void deleteEducationById (long id) {
    skillRepository.deleteById(id);
  }

}
