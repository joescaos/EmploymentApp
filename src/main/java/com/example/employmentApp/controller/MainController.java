package com.example.employmentApp.controller;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.IEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IEmploymentService employmentService;

    @GetMapping("/")
    public String mainView(Model model) {
        List<Employment> employmentList = employmentService.findAll();
        model.addAttribute("employments", employmentList);
        return "home";
    }
}
