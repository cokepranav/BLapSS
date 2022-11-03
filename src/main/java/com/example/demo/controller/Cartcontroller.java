package com.example.demo.controller;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Cartcontroller {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SecurityServices securityservices;
    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping("/cart/{customerId}")
    public String getCartByCustomerId(@PathVariable("customerId") int customerId, Model model) {
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("notinStock","");
        return "cart";
    }


//    @PostMapping("/update-cart")
    @RequestMapping(value = "/update_cart",method=RequestMethod.POST)
    public String updateCart(@RequestParam("customerId") int customerId,@RequestParam("id")int id,@RequestParam("size")String size,@RequestParam("prev")int prev,@RequestParam("quantity")int quantity, Model model){
        Product product= productRepository.getProductById(id);
        System.out.println(customerId);
        System.out.println(quantity);
        if(size.equals("S")){
            if((product.getSmallInStock()-(quantity-prev))>0){
                cartItemRepository.updateCartItem(customerId,id,quantity,size);
                productRepository.intoCartUpdate(id,size,product.getSmallInStock()-(quantity-prev));
            }
            else{
                int x=product.getSmallInStock()+prev;
//                model.addAttribute("notinStock","We have only "+()+" In Stock");
                return "redirect:/cart/" + String.valueOf(customerId)+"?q=Only+"+x+"+left!!";
            }
        }
        if(size.equals("M")){
            if(product.getMediumInStock()-(quantity-prev)>0){
                cartItemRepository.updateCartItem(customerId,id,quantity,size);
                productRepository.intoCartUpdate(id,size,product.getMediumInStock()-(quantity-prev));
            }
            else{
                  int x=product.getMediumInStock()+prev;
//                return "redirect:/cart/{customerId}q=?";
                  return "redirect:/cart/" + String.valueOf(customerId)+"?q=Only+"+x+"+left!!";
//                model.addAttribute("notinStock","We have only "+(product.getMediumInStock()+prev)+" In Stock");
            }
        }
        if(size.equals("L")){
            if(product.getLargeInStock()-(quantity-prev)>0){
                cartItemRepository.updateCartItem(customerId,id,quantity,size);
                productRepository.intoCartUpdate(id,size,product.getLargeInStock()-(quantity-prev));
            }
            else{
                  int x=product.getLargeInStock()+prev;
//                model.addAttribute("notinStock","We have only "+(product.getLargeInStock()+prev)+" In Stock");
                  return "redirect:/cart/" + String.valueOf(customerId)+"?q=Only+"+x+"+left!!";
            }
        }
//        model.addAttribute("notinStock","");
        model.addAttribute( "user",securityservices.findLoggedInCustomer());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        return "redirect:/cart/" + String.valueOf(customerId);
    }

    @PostMapping("/delete-from-cart")
    public String deletefromCart(@RequestParam("customerId") int customerId,@RequestParam("id")int id,@RequestParam("size")String size,@RequestParam("prev")int prev, Model model){
        Product product= productRepository.getProductById(id);
//        System.out.println(customerId);
        if(size.equals("S")){
            cartItemRepository.deleteCartItem(customerId,id,size);
            productRepository.intoCartUpdate(id,size,product.getSmallInStock()+prev);
        }
        if(size.equals("M")){
            cartItemRepository.deleteCartItem(customerId,id,size);
            productRepository.intoCartUpdate(id,size,product.getMediumInStock()+prev);
        }
        if(size.equals("L")){
            cartItemRepository.deleteCartItem(customerId,id,size);
            productRepository.intoCartUpdate(id,size,product.getLargeInStock()+prev);
        }
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("notinStock","");
        return "redirect:/cart/"+String.valueOf(customerId);
    }
}
