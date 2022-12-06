package com.example.employmentApp.controller;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.ICategoryService;
import com.example.employmentApp.service.IEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class PositionRequestController {

    @Autowired
    IEmploymentService employmentService;

    @GetMapping("/position/{id}")
    public String showPositionRequestForm(@PathVariable int id, Model model) {
        Employment employment = employmentService.getById(id);
        model.addAttribute("employment", employment);
        return "requests/formRequest";
    }
}
