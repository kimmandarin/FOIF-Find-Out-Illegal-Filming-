package com.example.foif.controller;

import com.example.foif.domain.FindPwd;
import com.example.foif.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindPasswordController {
    MemberService memberService;

    @Autowired
    public FindPasswordController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/findpassword")
    public String addForm(Model model){
        model.addAttribute("findPwd", new FindPwd());
        return "findPW";
    }

    @PostMapping(value = "/findpassword")
    public String form(@ModelAttribute("findPwd") FindPwd findPwd){
        memberService.findPassword(findPwd.getUsername());
        return "findPW";
    }
}
