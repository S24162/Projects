package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Skill;
import com.example.cv_thymeleaf.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

  public List<Skill> getSortedListById(Person person) {
    return skillRepository.findAllByPersonOrderByIdAsc(person);
  }

  public void deleteEducationById(long id) {
    skillRepository.deleteById(id);
  }

}
