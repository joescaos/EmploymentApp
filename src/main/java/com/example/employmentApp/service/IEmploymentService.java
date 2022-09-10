package com.example.employmentApp.service;

import com.example.employmentApp.model.Employment;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IEmploymentService {

    List<Employment> findAll();

    Employment getById(int id);

    void save(Employment employment);
}
