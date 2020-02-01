package com.ryszard.materialsandparts.controller;

import com.ryszard.materialsandparts.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "login-form";
    }

    @RequestMapping("/loginForm")
    public String processForm(
            @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login-form";
        } else {
            return "user-confirmation";
        }
    }
}