package com.example.foif.controller;

import com.example.foif.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    @RequestMapping(value = "/test")
    public String home() {
        return "test";
    }

    @RequestMapping(value = "/signUp")
    public String signup() {
        return "signUp";
    }

    @GetMapping(value = "/signIn")
    public String signIn(Model model) {
        return "signIn";
    }

    @RequestMapping(value = "/home")
    public String testIndex() {
        return "Index";
    }

    @PostMapping("/signIn")
    @ResponseBody
    public void form(@ModelAttribute Member member) {
        System.out.println(member.getPoliceStation());
        System.out.println(member.getUserName());
        System.out.println(member.getPoliceId());
        System.out.println(member.getPhoneNumber());
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());
        System.out.println(member.isAgreeBox());
    }
}
