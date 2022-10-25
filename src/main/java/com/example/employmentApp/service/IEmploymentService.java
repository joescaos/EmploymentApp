package com.example.employmentApp.service;

import com.example.employmentApp.model.Employment;

import java.util.List;
import java.util.Optional;

public interface IEmploymentService {

    List<Employment> findAll();

    Optional<Employment> getById(int id);

    void save(Employment employment);
}
