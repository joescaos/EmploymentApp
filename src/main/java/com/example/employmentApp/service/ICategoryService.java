package com.example.employmentApp.service;

import com.example.employmentApp.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    void save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Integer id);
}
