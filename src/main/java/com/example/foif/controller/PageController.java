package com.example.foif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/test")
    public String home() {
        return "Index";
    }

    @GetMapping(value = "/signup")
    public String signup() {
        System.out.println("signUp 페이지");
        return "signUp";
    }

    @GetMapping(value = "/signin")
    public String signIn() {
        System.out.println("signIn 페이지");
        return "signIn";
    }

    @GetMapping(value = "/home")
    public String testIndex() {
        System.out.println("Home 페이지");
        return "home";
    }
}
