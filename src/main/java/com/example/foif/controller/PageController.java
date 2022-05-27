package com.example.foif.controller;

import com.example.foif.algorithm.CompareVideo;
import com.example.foif.algorithm.VideoToImage;
import com.example.foif.domain.MemberDTO;
import com.example.foif.domain.UserDTO;
import com.example.foif.service.FileService;
import com.example.foif.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    MemberService memberService;
    FileService fileService;

    @Autowired
    public PageController(MemberService memberService, FileService fileService){
        this.memberService = memberService;
        this.fileService = fileService;
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

    @RequestMapping(value = "/sessiontest")
    public String sessionTest(HttpServletRequest request){
        HttpSession session = request.getSession();

        Long originalId = (Long)session.getAttribute("originalId");
        Long queryId = (Long)session.getAttribute("queryId");
        Long compareId = (Long)session.getAttribute("compareId");

        System.out.println(fileService.fileInfo(originalId));
        System.out.println(fileService.fileInfo(queryId));
        System.out.println(fileService.fileInfo(compareId));

        String originalFilePath = fileService.fileInfo(originalId);
        String compareFilePath = fileService.fileInfo(compareId);
        VideoToImage videoToImage = new VideoToImage();
//        videoToImage.videoToImage(originalFilePath, originalFilePath);

        String str1 = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest";
        String str2 = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\comparetest";

        CompareVideo compareVideo = new CompareVideo();
        compareVideo.compareVideo(183, 183, str1, str2);

        return "/home";
    }

    @GetMapping(value = "/user")
    public String userPage(Model model){
        MemberDTO memberDTO = memberService.userInfo();

        System.out.println("PoliceStation : " + memberDTO.getPoliceStation());
        System.out.println("PoliceId : " + memberDTO.getPoliceId());
        System.out.println("email : " + memberDTO.getEmail());
        System.out.println("Username : " + memberDTO.getUserName());
        System.out.println("PhoneNumber : " + memberDTO.getPhoneNumber());

        UserDTO userDTO = new UserDTO();

        userDTO.setPoliceId(memberDTO.getPoliceId());
        userDTO.setUserName(memberDTO.getUserName());
        userDTO.setEmail(memberDTO.getEmail());
        userDTO.setPhoneNumber(memberDTO.getPhoneNumber());

        switch (memberDTO.getPoliceStation()){
            case "02":
                userDTO.setPoliceStation("Seoul");
                break;
            case "031":
                userDTO.setPoliceStation("Kyonggi");
                break;
            case "032":
                userDTO.setPoliceStation("Incheon");
                break;
            case "054":
                userDTO.setPoliceStation("Busan");
            case "033":
                userDTO.setPoliceStation("Gangwon");
        }

        if(memberDTO.getEnabled() == true){
            userDTO.setRole("관리자");
        }
        if(memberDTO.getEnabled() == false){
            userDTO.setRole("유저");
        }

        model.addAttribute("userDTO", userDTO);
        return "user_page";
    }

    @RequestMapping("/toHome")
    public String toHome(){
        return "redirect:/homePage";
    }
}
