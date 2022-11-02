package com.example.demo.controller;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Cartcontroller {

    @Autowired
    SecurityServices securityservices;
    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping("/cart/{customerId}")
    public String getCartByCustomerId(@PathVariable("customerId") int customerId, Model model) {
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
}