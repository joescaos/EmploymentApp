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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;

@Controller
public class MainController {

    private static final String USER_SAVED_SUCCESS_MSG = "usuario creado";

    @Autowired
    private IEmploymentService employmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String mainView(Model model) {
        return "home";
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

        String rawPass = user.getPassword();
        String encryptedPass = passwordEncoder.encode(rawPass);
        Profile defaultUserProfile = new Profile();
        defaultUserProfile.setId(3);
        defaultUserProfile.setProfile("USUARIO");
        user.setPassword(encryptedPass);
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
    @GetMapping("/index")
    public String showIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();
       if (Objects.isNull(session.getAttribute("user"))) {
           User user = userService.findUserByUsername(username);
           user.setPassword(null);
           System.out.println("Usuario: " + user);
           session.setAttribute("user", user);
       }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }

   @GetMapping("/bcrypt/{text}")
   @ResponseBody
    public String encrypt(@PathVariable String text) {
        return String.format("%s Encriptado en bcrypt: %s", text, passwordEncoder.encode(text));
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
