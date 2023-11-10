package com.example.cv_thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationDTO {
  private String username;
  private String password;

  public String toString() {
    return "Register info: username: " + this.username + " password: " + this.password;
  }
}
