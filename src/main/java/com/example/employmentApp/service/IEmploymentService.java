package com.example.employmentApp.service;

import com.example.employmentApp.model.Employment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmploymentService {

    List<Employment> findAll();

    Employment getById(int id);

    void save(Employment employment);

    List<Employment> getHighlightedPositions();

    void deleteEmployment(Integer id);

    List<Employment> findByExample(Example<Employment> example);

    Page<Employment> findAll(Pageable pageable);
}
