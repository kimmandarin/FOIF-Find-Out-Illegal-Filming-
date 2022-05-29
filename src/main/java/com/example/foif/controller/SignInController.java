package com.example.foif.controller;

import com.example.foif.domain.SignIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @GetMapping(value = "/signIn")
    public String addForm(Model model){
        model.addAttribute("member", new SignIn());
        return "signIn";
    }

    @PostMapping("/signIn")
    public String form(@ModelAttribute SignIn signIn){
        return "redirect:/homePage";
    }
}
