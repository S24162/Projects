package com.example.cv_thymeleaf;

import com.example.cv_thymeleaf.model.*;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.repository.UserRepository;
import com.example.cv_thymeleaf.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CvThymeleafApplication {

  public static void main(String[] args) {
    SpringApplication.run(CvThymeleafApplication.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder,
                        AppUserService appUserService,
                        PersonService personService,
                        ExperienceService experienceService,
                        EducationService educationService,
                        SkillsService skillsService) {
    return args -> {
      if (roleRepository.findByAuthority("ADMIN").isPresent() && roleRepository.findByAuthority("USER").isPresent()) {
        System.out.println("CvThymeleafApplication.CommandLineRunner run : Presented roles: ADMIN, USER");

        Role adminRole = roleRepository.findByAuthority("ADMIN").get();
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        adminRoles.add(adminRole);
        userRoles.add(userRole);

        ApplicationUser newUser = new ApplicationUser("user",
           passwordEncoder.encode("user"), userRoles);
        appUserService.addAppUser(newUser);


        educationService.addEducation(new Education(1L, "Polish-Japanese Academy of Information Technology\n" +
           "Project management · (october 2021 - 2026)", null));
        educationService.addEducation(new Education(2L, "Software Development Academy\n" +
           "Java · (november 2022 - august 2023)", null));
        skillsService.addSkill(new Skill(1L, "Java", null));
        skillsService.addSkill(new Skill(2L, "Spring Framework", null));
        skillsService.addSkill(new Skill(3L, "SQL", null));


        Person newPerson = personService.getBlankPerson();
        newPerson.setApplicationUser(newUser);
        newPerson.setName("Eugeniusz Grakovitch");
        newPerson.setBrand("JUNIOR JAVA");
        newPerson.setAbout("Student of PJATK (Polish-Japanese Academy of Information Technology, part-time mode, specialization.. professional experience related to motorcycles.");
        newPerson.getEducationSet().add(educationService.getEducationById(1));
        newPerson.getEducationSet().add(educationService.getEducationById(2));
        newPerson.getSkillsSet().add(skillsService.getSkillById(1));
        newPerson.getSkillsSet().add(skillsService.getSkillById(2));
        newPerson.getSkillsSet().add(skillsService.getSkillById(3));
        newPerson.setInterests("Motorcycles\n" +
           "Guitars");

        personService.addPerson(newPerson);
        newUser.setPerson(newPerson);
        appUserService.addAppUser(newUser);

        experienceService.getExperienceList().forEach(experience -> {
          newPerson.getExperienceSet().add(experience);
          experience.setPerson(newPerson);
          experienceService.addExperience(experience);
        });

        educationService.getEducationList().forEach(education -> {
          education.setPerson(newPerson);
          educationService.addEducation(education);
        });

        skillsService.getSkillList().forEach(skill -> {
          skill.setPerson(newPerson);
          skillsService.addSkill(skill);
        });

//        newUser = new ApplicationUser("admin", passwordEncoder.encode("admin"), adminRoles);
//        appUserService.addAppUser(newUser);



      } else {
        System.out.println("Roles ADMIN or USER is not found. Roles is not defined.");
      }
    };
  }

//  @Bean
//  CommandLineRunner run(JdbcUserDetailsManager userDetailsManager, DataSource dataSource) {
//    return args -> {
//      var admin = User.builder()
//         .username("admin")
//         .password(passwordEncoder().encode("admin"))
//         .roles("USER", "ADMIN")
//         .build();
//      UserDetails user = User.builder()
//         .username("user")
//         .password(passwordEncoder().encode("user"))
//         .roles("USER")
//         .build();
//      JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//      users.createUser(user);
//      users.createUser(admin);
//    };
//  }

}
