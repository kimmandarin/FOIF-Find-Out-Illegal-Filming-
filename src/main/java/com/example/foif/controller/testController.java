package com.example.foif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping(value ="/home")
    public String main(){
        return "index";
    }

    @RequestMapping(value = "/test")
    public String home(){
        return "test";
    }

    @RequestMapping(value = "/signup")
    public String signup() { return "sign_up";}

    @RequestMapping(value = "/signin")
    public String signin() { return "sign_in";}

    @RequestMapping(value = "/testIndex")
    public String testIndex() { return "testIndex";}
}
