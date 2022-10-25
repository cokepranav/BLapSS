package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Hellocontroller {
    @GetMapping({"/hi"})
    public void Home(){
        System.out.println("jsdkklasd");
//        return "template/home";
    }
}
