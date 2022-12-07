package com.example.employmentApp.controller;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.model.Request;
import com.example.employmentApp.model.User;
import com.example.employmentApp.service.IEmploymentService;
import com.example.employmentApp.service.IRequestService;
import com.example.employmentApp.service.IUserService;
import com.example.employmentApp.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping("/request")
public class PositionRequestController {

    private static final String RQST_APPLIED_SUCCESS_MSG = "Has aplicado exitosamente a la vacante";

    @Value("${employmentapp.path.files}")
    private String FILE_PATH;

    @Autowired
    IRequestService requestService;

    @Autowired
    IEmploymentService employmentService;

    @Autowired
    IUserService userService;

    @GetMapping("/position/{id}")
    public String showPositionRequestForm(@PathVariable int id, Model model) {
        Request request = new Request();
        Employment employment = employmentService.getById(id);
        model.addAttribute("employment", employment);
        model.addAttribute("request", request);
        return "requests/formRequest";
    }

    @PostMapping("/save")
    public String applyForPosition(Request request, BindingResult result,
                                   RedirectAttributes attributes, @RequestParam("archivoCV") MultipartFile file,
                                   Authentication auth, Model model, HttpSession session) {
        String userName = auth.getName();

        if (result.hasErrors()) {
            return "requests/formRequest";
        }

        if (!file.isEmpty()) {
            String fileName = Utility.saveFile(file, FILE_PATH);
            if (Objects.nonNull(fileName)) {
                request.setFile(fileName);
            }
        }
        User user = userService.findUserByUsername(userName);
        request.setUser(user);
        request.setDate(LocalDate.now());
        requestService.saveRequest(request);
        attributes.addFlashAttribute("msg", RQST_APPLIED_SUCCESS_MSG);

        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, false));
    }
}
