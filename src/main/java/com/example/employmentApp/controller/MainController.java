package com.example.employmentApp.controller;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.model.Profile;
import com.example.employmentApp.model.User;
import com.example.employmentApp.service.ICategoryService;
import com.example.employmentApp.service.IEmploymentService;
import com.example.employmentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collections;

@Controller
public class MainController {

    private static final String USER_SAVED_SUCCESS_MSG = "usuario creado";

    @Autowired
    private IEmploymentService employmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public String mainView(Model model) {
        return "home";
    }

    @GetMapping("/details/{id}")
    public String positionDetails(@PathVariable int id, Model model) {
        Employment employment = employmentService.getById(id);
        model.addAttribute("employment", employment);
        return "details";
    }
    @GetMapping("/signup")
    public String signup(User user) {
        return "signupForm";
    }
    @PostMapping("/signup")
    public String saveUser(User user, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return "signupForm";
        }
        Profile defaultUserProfile = new Profile();
        defaultUserProfile.setId(3);
        defaultUserProfile.setProfile("USUARIO");
        user.setProfiles(Collections.singletonList(defaultUserProfile));
        user.setDateCreated(LocalDate.now());
        user.setStatus(1);
        userService.saveUser(user);
        attributes.addFlashAttribute("msg", USER_SAVED_SUCCESS_MSG);
        return "redirect:/users/index";
    }
    @GetMapping("/search")
    public String search(@ModelAttribute("search") Employment employment, Model model) {
        System.out.println("Buscando por: " + employment);

        ExampleMatcher matcher = ExampleMatcher
                .matching().withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Employment> example = Example.of(employment, matcher);
        model.addAttribute("employments", employmentService.findByExample(example));
        return "home";
    }
    @ModelAttribute
    public void setGenerics(Model model) {
        Employment employmentSearch = new Employment();
        employmentSearch.resetImage();
        model.addAttribute("employments", employmentService.getHighlightedPositions());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("search", employmentSearch);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
