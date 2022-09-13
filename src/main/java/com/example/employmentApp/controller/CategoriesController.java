package com.example.employmentApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String categoriesIndex(Model model) {
    return "categories/categoriesList";
  }

  @GetMapping("/create")
  public String createCategory() {
    return "categories/categoryForm";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String saveCategory(@RequestParam String name, @RequestParam String description) {
    System.out.println("Categoria: " + name);
    System.out.println("Descripcion: "+ description);
    return "categories/categoriesList";
  }

}
