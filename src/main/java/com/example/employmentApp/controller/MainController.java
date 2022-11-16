package com.example.employmentApp.controller;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.model.Profile;
import com.example.employmentApp.model.User;
import com.example.employmentApp.service.IEmploymentService;
import com.example.employmentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private static final String USER_SAVED_SUCCESS_MSG = "usuario creado";

    @Autowired
    private IEmploymentService employmentService;

    @Autowired
    private IUserService userService;

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
    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("employments", employmentService.getHighlightedPositions());
    }
}
