package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.repository.ExperienceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

  private final ExperienceRepo experienceRepo;

  public void addExperience(Experience experience) {
    experienceRepo.save(experience);
  }

  public List<Experience> getExperienceList() {
    return experienceRepo.findAll();
  }

  public Experience getExperienceById(Long id) {
    return experienceRepo.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    experienceRepo.deleteById(id);
  }


}
