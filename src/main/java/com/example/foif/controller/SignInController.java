package com.example.foif.controller;

import com.example.foif.domain.SignIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @PostMapping("/signin")
    public String form(@ModelAttribute SignIn signIn){
        return "redirect:/home";
    }
}
