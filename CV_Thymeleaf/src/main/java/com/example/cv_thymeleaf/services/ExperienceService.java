package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

  private final ExperienceRepository experienceRepository;

  public void addExperience(Experience experience) {
    experienceRepository.save(experience);
  }

  public List<Experience> getExperienceList() {
    return experienceRepository.findAll();
  }

  public Experience getExperienceById(Long id) {
    return experienceRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    experienceRepository.deleteById(id);
  }

  public List<Experience> getSortedListByDateFrom(ApplicationUser appUser) {
    return experienceRepository.
       findAllByPersonOrderByDateFromDesc(appUser.getPerson());
  }


}
