package com.example.foif.controller;

import com.example.foif.member.MemberDTO;
import com.example.foif.member.SignIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

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

    @PostMapping("/signup")
    public String form(@ModelAttribute MemberDTO memberDTO) {
        System.out.println(memberDTO.getPoliceStation());
        System.out.println(memberDTO.getUserName());
        System.out.println(memberDTO.getPoliceId());
        System.out.println(memberDTO.getPhoneNumber());
        System.out.println(memberDTO.getEmail());
        System.out.println(memberDTO.getPassword());
        return "redirect:/test";
    }

    @PostMapping("/signin")
    public String form(@ModelAttribute SignIn signIn){
        System.out.println(signIn.getEmail());
        System.out.println(signIn.getPassword());
        return "redirect:/home";
    }
}
