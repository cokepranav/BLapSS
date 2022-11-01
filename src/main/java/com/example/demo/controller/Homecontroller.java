package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

    @Autowired
    private SecurityServices securityservices;
    @Autowired
    private ProductRepository productRepository;
//    @GetMapping({"/hi"})
//    public void Home(){
//        System.out.println("jsdkklasd");
////        return "template/home";
//    }
    @GetMapping({"/","/index","/home"})
    public String index(Model model){model.addAttribute("naam",securityservices.findLoggedInUsername());model.addAttribute("products",productRepository.sortbyINV());return "index";
    }
}
