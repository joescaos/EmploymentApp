package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Integer> {

}
