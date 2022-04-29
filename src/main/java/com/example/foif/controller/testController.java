package com.example.foif.controller;

import com.example.foif.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    @GetMapping(value = "/test")
    public String home() {
        return "Index";
    }

    @RequestMapping(value = "/signup")
    public String signup() {
        System.out.println("signUp 페이지");
        return "signUp";
    }

    @RequestMapping(value = "/signin")
    public String signIn() {
        System.out.println("signIn 페이지");
        return "signIn";
    }

    @RequestMapping(value = "/home")
    public String testIndex() {
        System.out.println("Home 페이지");
        return "home";
    }

    @PostMapping("/signup")
    public String form(@ModelAttribute Member member) {
        System.out.println(member.getPoliceStation());
        System.out.println(member.getUserName());
        System.out.println(member.getPoliceId());
        System.out.println(member.getPhoneNumber());
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());
        System.out.println(member.isAgreeBox());
        return "redirect:/test";
    }
}
