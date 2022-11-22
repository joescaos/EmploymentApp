package com.example.employmentApp.controller;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.ICategoryService;
import com.example.employmentApp.service.IEmploymentService;
import com.example.employmentApp.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/index-paginated")
    public String showIndexPaginated(Model model, Pageable pageable) {
        Page<Employment> employmentList = employmentService.findAll(pageable);
        model.addAttribute("employments", employmentList);
        return "positions/positionsList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("msg", "La vacante fue eliminada!");
        employmentService.deleteEmployment(id);
        return "redirect:/positions/index";
    }

    @GetMapping("/create")
    public String createPosition(Employment employment) {
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
        attributes.addFlashAttribute("msg", POSITION_SAVED_SUCCESS_MSG);
        return "redirect:/positions/index";
    }
    @GetMapping("/edit/{id}")
    public String EditEmployment(@PathVariable int id, Model model) {
        Employment employment = employmentService.getById(id);
        model.addAttribute("employment", employment);
        return "positions/positionForm";
    }

    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("categories", categoryService.findAll());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
