package com.example.cv_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class EducationController {

  @GetMapping("education")
  public String getHome(ModelMap map){
    map.put("one", "One text");
    return "education";
  }
}
