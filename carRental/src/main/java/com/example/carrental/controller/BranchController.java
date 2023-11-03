package com.example.carrental.controller;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Car;
import com.example.carrental.service.BranchService;
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

public class BranchController {
    private final BranchService branchService;

    @GetMapping("/addBranch")
    public String getAddBranch() {
        return "addNewBranch";
    }

    @PostMapping("/addBranch")
    public RedirectView postAddBranch(Branch branch) {
        branchService.addBranch(branch);
        return new RedirectView("/branches");
    }

    @GetMapping("/branches")
    public String getBranchList(Model model) {
        List<Branch> branchList = branchService.getBranchList();
        model.addAttribute("branch", branchList);
        return "branchList";
    }

    @GetMapping("/editBranch/{id}")
    public String getEditBranch(@PathVariable Long id, Model model) {
        Branch branchById = branchService.getBranchById(id);
        model.addAttribute("branch", branchById);
        return "editBranch";
    }


    @PostMapping("/editBranch/{id}")
    public RedirectView postEditBranch(Branch editBranch) {
        branchService.editBranch(editBranch);
        return new RedirectView("/branches");
    }


    @PostMapping("/deleteBranch/{id}")
    public RedirectView deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return new RedirectView("/branches");
    }
}
