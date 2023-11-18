package com.example.cv_thymeleaf.services;

import com.example.cv_thymeleaf.model.ApplicationUser;
import com.example.cv_thymeleaf.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  private final AppUserRepository appUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    if (!username.equals("Eugenio")) throw new UsernameNotFoundException("Not a user");
//
//    Set<Role> roles = new HashSet<>();
//    roles.add(new Role(1, "USER"));
//
//    return new ApplicationUser(1L, "Eugenio", passwordEncoder.encode("user"),roles);

//    System.out.println("AppUserService( implements UserDetailsService).method 'loadUserByUsername' is invoked");
    return appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
  }

  public ApplicationUser getBlankUser(String username, String password) {

    ApplicationUser blankUser = new ApplicationUser();
    blankUser.setUsername(username);
    blankUser.setPassword(passwordEncoder.encode(password));
    return blankUser;
  }

  public ApplicationUser addAppUser(ApplicationUser appUser) {
    System.out.println("### New user with username '" + appUser.getUsername() + "' is created by 'AppUserService.addAppUser'" );
    return appUserRepository.save(appUser);
  }

  public void deleteAppUser(Long id) {
    appUserRepository.deleteById(id);
  }

  public ApplicationUser findUserByUsername(String username) {
    return appUserRepository.findByUsername(username).orElse(new ApplicationUser());
  }

}
