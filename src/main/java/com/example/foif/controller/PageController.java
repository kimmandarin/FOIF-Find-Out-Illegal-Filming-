package com.example.foif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "/test")
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

    @RequestMapping(value = "/findpwd")
    public String findPwd(){
        System.out.println("비밀번호 찾기 페이지");
        return "findPW";
    }

    @RequestMapping(value = "/privacypolicy")
    public String privacyPolicy(){
        return "privacypolicy";
    }
}
