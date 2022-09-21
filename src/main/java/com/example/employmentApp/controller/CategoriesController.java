package com.example.employmentApp.controller;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

  private static final String CATEGORY_SAVED_SUCCESS_MSG = "Categoria guardada";
  @Autowired
  private ICategoryService categoryService;
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String categoriesIndex(Model model) {
    List<Category> categoryList = categoryService.findAll();
    model.addAttribute("categories", categoryList);
    return "categories/categoriesList";
  }

  @GetMapping("/create")
  public String createCategory() {
    return "categories/categoryForm";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String saveCategory(Category category, BindingResult result, RedirectAttributes attributes) {
    if(result.hasErrors()) {
        return "categories/categoryForm";
    }
    categoryService.save(category);
    attributes.addFlashAttribute("msg", CATEGORY_SAVED_SUCCESS_MSG);
    return "redirect:/categories/index";
  }

}
