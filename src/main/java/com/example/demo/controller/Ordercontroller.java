package com.example.demo.controller;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.OrderItemRepository;
import com.example.demo.dao.ReviewRepository;
import com.example.demo.models.*;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Ordercontroller {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    SecurityServices securityServices;


    @GetMapping("/cartTOorder/{customerId}")
    public String cartToorder(@PathVariable("customerId") int customerId, Model model){
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        List<CartItem> cartItems=cartItemRepository.getCartItemsBycustomerId(customerId);
        for(int i=0;i<cartItems.size();i++){
            CartItem cartItem=cartItems.get(i);
            orderItemRepository.addToorder(cartItem.getProductId(),customerId,cartItem.getQuantity(),cartItem.getSize());
        }
        cartItemRepository.deleteCartItemofCustomer(customerId);
        return "redirect:/cart/" + String.valueOf(customerId)+"?f=CartEmpty";
    }

    @GetMapping("/orders/{customerId}")
    public String Orderspage(@PathVariable("customerId") int customerId,Model model){
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityServices.findLoggedInCustomer());
        model.addAttribute("naam",securityServices.findLoggedInUsername());
        List<Product> ordereditems=orderItemRepository.getOrderitemasProduct(customerId);
        for(int i=0;i<ordereditems.size();i++){
            List<Review> r=reviewRepository.gethisreview(ordereditems.get(i).getProductId(),customerId);
            if(r.isEmpty()){
                ordereditems.get(i).setMediumInStock(0);
            }
            else{
                ordereditems.get(i).setMediumInStock(1);
            }
        }
        model.addAttribute("cartItems", ordereditems);
        return "orders";
    }
}
