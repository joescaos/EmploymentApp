package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private List<Category> categoryList;

    public CategoryServiceImpl() {
        categoryList = new LinkedList<>();

        Category sales = new Category(1, "Ventas", "Area de ventas");
        Category accounting = new Category(2, "Contabilidad", "Area de contabilidad");
        Category transportation = new Category(3, "Transporte", "Area de transporte");
        Category software = new Category(4, "Informatica", "Area de informatica y software");
        Category construction = new Category(5, "Construccion", "Area de construccion");

        categoryList.add(sales);
        categoryList.add(accounting);
        categoryList.add(transportation);
        categoryList.add(software);
        categoryList.add(construction);
    }

    @Override
    public void save(Category category) {
        categoryList.add(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public Category findById(Integer id) {
        for(Category category : categoryList) {
            if(category.getId() == id) {
                return category;
            }
        }
        return null;
    }
}
