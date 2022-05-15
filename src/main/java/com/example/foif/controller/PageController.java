package com.example.foif.controller;

import com.example.foif.domain.MemberDTO;
import com.example.foif.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {

    MemberService memberService;

    @Autowired
    public PageController(MemberService memberService){
        this.memberService = memberService;
    }

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

    @RequestMapping(value = "/homePage")
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

    @RequestMapping(value = "/termsofservice")
    public String termsofSerice(){
        return "termsofservice";
    }

    @RequestMapping(value = "/result")
    public String resultPage(){
        return "result_page";
    }

    @GetMapping(value = "/user")
    public String userPage(Model model){
        MemberDTO memberDTO = memberService.userInfo();
        model.addAttribute("memberDTO", memberDTO);
        return "user_page";
    }
}
