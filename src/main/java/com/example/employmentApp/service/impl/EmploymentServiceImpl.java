package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.repositories.EmploymentsRepository;
import com.example.employmentApp.service.IEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentServiceImpl implements IEmploymentService {

    @Autowired
    private EmploymentsRepository employmentsRepository;

    public EmploymentServiceImpl() {
    }

    @Override
    public List<Employment> findAll() {
        return employmentsRepository.findAll();
    }

    @Override
    public Optional<Employment> getById(int id) {
        return employmentsRepository.findById(id);
    }

    @Override
    public void save(Employment employment) {
        employmentsRepository.save(employment);
    }
}
