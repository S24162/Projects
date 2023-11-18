package com.example.cv_thymeleaf;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Skill;
import com.example.cv_thymeleaf.repository.RoleRepository;
import com.example.cv_thymeleaf.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CvThymeleafApplication {

  public static void main(String[] args) {
    SpringApplication.run(CvThymeleafApplication.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository,
                        AppUserService appUserService,
                        PersonService personService,
                        ExperienceService experienceService,
                        EducationService educationService,
                        SkillsService skillsService,
                        AuthenticationService authenticationService) {
    return args -> {
      if (roleRepository.findByAuthority("ADMIN").isPresent() && roleRepository.findByAuthority("USER").isPresent()) {
        System.out.println("CvThymeleafApplication.CommandLineRunner run : Presented roles: ADMIN, USER");


        authenticationService.registerUser("admin", "admin");
        authenticationService.registerUser("user", "user");
        ApplicationUser user2 = authenticationService.registerUser("user2", "user2");
        user2.getPerson().setBrand("name: user2");
        user2.getPerson().setName("password: user2");
        appUserService.addAppUser(user2);
        authenticationService.registerUser("user3", "user3");


        educationService.addEducation(new Education(1L, "Polish-Japanese Academy of Information Technology\n" +
           "Project management · (october 2021 - 2026)", null));
        educationService.addEducation(new Education(2L, "Software Development Academy\n" +
           "Java · (november 2022 - august 2023)", null));
        skillsService.addSkill(new Skill(1L, "Java", null));
        skillsService.addSkill(new Skill(2L, "Spring Framework", null));
        skillsService.addSkill(new Skill(3L, "SQL", null));


        Person personUser = personService.findById(1);
        ApplicationUser newUser = appUserService.findUserByUsername("user");
        personUser.setApplicationUser(newUser);
        personUser.setName("Eugeniusz Grakovitch");
        personUser.setBrand("JUNIOR JAVA");
        personUser.setAbout("Student of PJATK (Polish-Japanese Academy of Information Technology, part-time mode, specialization \"Project Management\", 3rd year).\n" +
           "Graduate of the intensive \"Java from scratch\" course at the SDA Academy. (298 hours/8 months)\n" +
           "My deepest knowledge is in the areas of Java and SQL.\n" +
           "\n" +
           "I want to find a job with the possibility of development in Java, Spring, SQL and related fields. During my studies, I had a course on the basics of HTML, CSS, JavaScript, Git and a lot of work with Hibernate. I am constantly focused on further growth and I am looking for a job that will enable me to do so. \n" +
           "\n" +
           "Previous professional experience related to motorcycles.");
        personUser.getEducationSet().add(educationService.getEducationById(1));
        personUser.getEducationSet().add(educationService.getEducationById(2));
        personUser.getSkillsSet().add(skillsService.getSkillById(1));
        personUser.getSkillsSet().add(skillsService.getSkillById(2));
        personUser.getSkillsSet().add(skillsService.getSkillById(3));
        personUser.setInterests("Motorcycles\n" +
           "Guitars");

        personService.addPerson(personUser);
        newUser.setPerson(personUser);
        appUserService.addAppUser(newUser);

        experienceService.getExperienceList().forEach(experience -> {
          personUser.getExperienceSet().add(experience);
          experience.setPerson(personUser);
          experienceService.addExperience(experience);
        });

        educationService.getEducationList().forEach(education -> {
          education.setPerson(personUser);
          educationService.addEducation(education);
        });

        skillsService.getSkillList().forEach(skill -> {
          skill.setPerson(personUser);
          skillsService.addSkill(skill);
        });

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
