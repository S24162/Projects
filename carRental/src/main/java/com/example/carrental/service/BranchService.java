package com.example.carrental.service;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Branch;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public void addBranch(Branch branch) {
        branchRepository.save(branch);
    }
    public List<Branch> getBranchList() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    public void editBranch(Branch editBranch) {
        branchRepository.save(editBranch);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

}
