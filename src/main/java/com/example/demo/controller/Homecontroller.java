package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {
//    @GetMapping({"/hi"})
//    public void Home(){
//        System.out.println("jsdkklasd");
////        return "template/home";
//    }
    @GetMapping({"/","/index","/home"})
    public String index(){
        return "index";
    }
}
