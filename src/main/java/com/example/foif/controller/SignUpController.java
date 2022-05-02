package com.example.foif.controller;

import com.example.foif.domain.Member;
import com.example.foif.domain.MemberDTO;
import com.example.foif.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    MemberService memberService;

    @Autowired
    public SignUpController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value="/signUp")
    public String addForm(Model model){
        model.addAttribute("memberDTO", new Member());
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String form(@ModelAttribute("memberDTO") Member memberDTO) {
        //memberService.joinMember(memberDTO);
        return "redirect:/test";
    }
}
