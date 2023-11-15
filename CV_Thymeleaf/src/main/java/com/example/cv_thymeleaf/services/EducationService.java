package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Experience;
import com.example.cv_thymeleaf.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

  private final EducationRepository educationRepository;

  public Education addEducation(Education education) {
    return educationRepository.save(education);
  }

  public Education getBlankEducation() {
    Education newBlankEduc = new Education();
    newBlankEduc.setText("Here will be description of your education");
    return newBlankEduc;
  }

  public Education getEducationById(long id) {
    if (educationRepository.findById(id).isPresent()) {
      return educationRepository.findById(id).get();
    } else return null;
  }

  public List<Education> getEducationList() {
      return educationRepository.findAll();
  }

  public void deleteEducationById (long id) {
    educationRepository.deleteById(id);
  }

  public List<Education> getSortedListById(ApplicationUser appUser) {
    return educationRepository.findAllByPersonOrderByIdAsc(appUser.getPerson());
  }
}
