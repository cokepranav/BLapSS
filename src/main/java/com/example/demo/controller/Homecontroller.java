package com.example.demo.controller;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Category;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Homecontroller {

    @Autowired
    private SecurityServices securityservices;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping({"/","/index","/home"})
    public String index(Model model){
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        model.addAttribute("naam",securityservices.findLoggedInUsername());
        model.addAttribute("shirts",productRepository.sortbyINVS());
        model.addAttribute("pants",productRepository.sortbyINVP());
        return "index";
    }
}
