package com.example.employmentApp.controller;

import com.example.employmentApp.model.User;
import com.example.employmentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public String showIndex(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/usersList";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("msg", "El usuario fue eliminado!");
        userService.deleteUser(id);
        return "redirect:/users/index";
    }

}
