package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {

}
