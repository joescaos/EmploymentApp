package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.repositories.CategoriesRepository;
import com.example.employmentApp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategoryServiceImpl() {
    }

    @Override
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> category = categoriesRepository.findById(id);
        if(category.isPresent()) {
            return category.get();
        }
        return null;
    }
}
