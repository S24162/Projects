package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.model.Education;
import com.example.cv_thymeleaf.model.Person;
import com.example.cv_thymeleaf.model.Role;
import com.example.cv_thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    if (!username.equals("Eugenio")) throw new UsernameNotFoundException("Not a user");
//
//    Set<Role> roles = new HashSet<>();
//    roles.add(new Role(1, "USER"));
//
//    return new ApplicationUser(1L, "Eugenio", passwordEncoder.encode("user"),roles);

    System.out.println("In the UserDetailService (UserService); method 'loadUserByUsername' ");
    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
  }

  public ApplicationUser getBlankUser() {

    Role blankRole = new Role();
    blankRole.setAuthority("blank Authority");

    Set<Role> roles = new HashSet<>();
    roles.add(blankRole);

    ApplicationUser blankUser = new ApplicationUser();

    blankUser.setUsername("blank user");
    blankUser.setAuthorities(roles);

    blankUser.setPerson(new Person());
    blankUser.getPerson().getEducationSet().add(new Education(999L,"facts will come out", null));

    return blankUser;
  }

  public ApplicationUser addAppUser(ApplicationUser appUser) {
    return userRepository.save(appUser);
  }
}
