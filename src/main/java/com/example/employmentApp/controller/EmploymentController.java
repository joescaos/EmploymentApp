package com.example.employmentApp.controller;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.IEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/positions")
public class EmploymentController {

    private static final String POSITION_SAVED_SUCCESS_MSG = "Registro guardado";
    @Autowired
    private IEmploymentService employmentService;

    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Employment> employmentList = employmentService.findAll();
        model.addAttribute("employments", employmentList);
        return "positions/positionsList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        System.out.println("Borrando vacante con id: "+ id);
        model.addAttribute("id", id);
        return "message";
    }

    @GetMapping("/create")
    public String createPosition(Employment employment) {
        return "positions/positionForm";
    }

    @PostMapping("/save")
    public String save(Employment employment, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "positions/positionForm";
        }
        employmentService.save(employment);
        attributes.addFlashAttribute("msg", POSITION_SAVED_SUCCESS_MSG);
        return "redirect:/positions/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
