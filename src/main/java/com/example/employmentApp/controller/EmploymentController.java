package com.example.employmentApp.controller;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.ICategoryService;
import com.example.employmentApp.service.IEmploymentService;
import com.example.employmentApp.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/positions")
public class EmploymentController {

    private static final String POSITION_SAVED_SUCCESS_MSG = "Registro guardado";

    @Value("${employmentapp.path.images}")
    private String path;
    @Autowired
    private IEmploymentService employmentService;

    @Autowired
    private ICategoryService categoryService;

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
    public String createPosition(Employment employment, Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "positions/positionForm";
    }

    @PostMapping("/save")
    public String save(Employment employment, BindingResult result,
                       RedirectAttributes attributes,
                       @RequestParam("imageFile") MultipartFile file) {
        if(result.hasErrors()){
            return "positions/positionForm";
        }

        if(!file.isEmpty()) {
            String imageName = Utility.saveFile(file, path);
            if(Objects.nonNull(imageName)) {
                employment.setImage(imageName);
            }
        }

        employmentService.save(employment);
        System.out.println(employment);
        attributes.addFlashAttribute("msg", POSITION_SAVED_SUCCESS_MSG);
        return "redirect:/positions/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
