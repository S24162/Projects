package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/addCar")
    public String getAddCar() {
        return "addNewCar";
    }

    @PostMapping("/addCar")
    public RedirectView postAddCar(Car car) {
        carService.addCar(car);
        return new RedirectView("/cars");
    }

    @GetMapping("/cars")
    public String getCarList(Model model) {
        List<Car> carList = carService.getCarList();
        model.addAttribute("car", carList);
        return "carList";
    }

    @GetMapping("/editCar/{vinNumber}")
    public String getEditCar(@PathVariable String vinNumber, Model model) {
        Car carById = carService.getCarById(vinNumber);
        model.addAttribute("car", carById);
        return "editCar";
    }


    @PostMapping("/editCar/{vinNumber}")
    public RedirectView postEditCar(Car editCar) {
        carService.editCar(editCar);
        return new RedirectView("/cars");
    }


    @PostMapping("/deleteCar/{vinNumber}")
    public RedirectView deleteCar(@PathVariable String vinNumber) {
        carService.deleteCar(vinNumber);
        return new RedirectView("/cars");
    }






}
