package com.example.employmentApp.service;

import com.example.employmentApp.model.Category;

import java.util.List;

public interface ICategoryService {
    void save(Category category);
    List<Category> findAll();
    Category findById(Integer id);
}
