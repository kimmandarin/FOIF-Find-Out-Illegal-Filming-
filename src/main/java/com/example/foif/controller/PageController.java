package com.example.foif.controller;

import com.example.foif.algorithm.CompareVideo;
import com.example.foif.algorithm.VideoToImage;
import com.example.foif.domain.MemberDTO;
import com.example.foif.domain.Result;
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
        return "signUp";
    }

    @RequestMapping(value = "/signin")
    public String signIn() {
        return "signIn";
    }

    @RequestMapping(value = "/homePage")
    public String testIndex() {
        return "home";
    }

    @RequestMapping(value = "/findpwd")
    public String findPwd(){
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

    @GetMapping(value = "/getresult")
    public String sessionTest(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Result result = new Result();

        Long originalId = (Long)session.getAttribute("originalId");
        Long queryId = (Long)session.getAttribute("queryId");
        Long compareId = (Long)session.getAttribute("compareId");

        String strTemp1 = fileService.fileInfo(originalId);
        String strTemp2 = fileService.fileInfo(compareId);
        String strTemp3 = fileService.fileInfo(queryId);

        String originalFilePath = strTemp1.substring(0, strTemp1.length()-4);
        String compareFilePath = strTemp2.substring(0, strTemp2.length()-4);
        String queryFilePath = strTemp3.substring(0, strTemp3.length()-4);

        VideoToImage videoToImage = new VideoToImage();
        videoToImage.videoToImage(strTemp1, originalFilePath);
        videoToImage.videoToImage(strTemp2, compareFilePath);

        CompareVideo compareVideo = new CompareVideo();
        compareVideo.compareVideo(30, 30, originalFilePath, compareFilePath, result);

        String[] resultStr = result.getResult();
        String[] correlStr = result.getCorrel();
        String[] intersectStr = result.getIntersect();
        String[] bhattacharyyaStr = result.getBhattacharyya();

        model.addAttribute("result", result);
        return "/result_page";
    }

    @GetMapping(value = "/user")
    public String userPage(Model model){
        MemberDTO memberDTO = memberService.userInfo();

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
