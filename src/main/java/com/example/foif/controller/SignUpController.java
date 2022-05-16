package com.example.foif.controller;

import com.example.foif.domain.Member;
import com.example.foif.domain.MemberDTO;
import com.example.foif.domain.Role;
import com.example.foif.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    MemberService memberService;
    Member member;
 //   PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpController(MemberService memberService, Member member){
        this.member = member;
        this.memberService = memberService;
      //  this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value="/signUp")
    public String addForm(Model model){
        model.addAttribute("member", new Member());
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String form(@Validated @ModelAttribute("member") Member member) {
        MemberDTO memberDTO = new MemberDTO();

        System.out.println(member.getEmail());
        System.out.println(member.getPoliceId());
        System.out.println(member.getPhoneNumber());
        System.out.println(member.getUserName());
        System.out.println(member.getPassword());
        System.out.println(member.getPoliceStation());

     //   String encodedPassword = passwordEncoder.encode(member.getPassword());

        memberDTO.setEmail(member.getEmail());
        memberDTO.setPoliceId(member.getPoliceId());
        memberDTO.setPhoneNumber(member.getPhoneNumber());
        memberDTO.setUserName(member.getUserName());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setPoliceStation(member.getPoliceStation());
        memberDTO.setEnabled(true);
        Role role = new Role();
        role.setId(1L);
        memberDTO.getRoles().add(role);

        memberService.joinMember(memberDTO);
        return "signIn";
    }
}
