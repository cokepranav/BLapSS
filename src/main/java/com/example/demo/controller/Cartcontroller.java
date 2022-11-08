package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.models.*;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class Cartcontroller {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SecurityServices securityservices;
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CustomerAddressRepository customerAddressRepository;

    @Autowired
    CustomerPhoneRepository customerPhoneRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/cart/{customerId}")
    public String getCartByCustomerId(@PathVariable("customerId") int customerId, Model model) {
        List<CustomerAddress> customerAddresses= customerAddressRepository.getAddressBycustId(customerId);
        List<CustomerPhoneNumber> customerPhones=customerPhoneRepository.getPhoneBycustId(customerId);
        System.out.println(customerAddresses);
        model.addAttribute("customerPhones",customerPhones);
        model.addAttribute("customerAddresses",customerAddresses);
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        model.addAttribute("naam",securityservices.findLoggedInUsername());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("notinStock","");
        List<CartItem> total=cartItemRepository.getTotal(customerId);
        float sum= 0.0F;
        for(int i=0;i<total.size();i++){
            sum=sum+total.get(i).getUnitPrice();
        }
        model.addAttribute("toto",sum);
        return "cart";
    }

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
            }
        }
        if(size.equals("L")){
            if(product.getLargeInStock()-(quantity-prev)>0){
                cartItemRepository.updateCartItem(customerId,id,quantity,size);
                productRepository.intoCartUpdate(id,size,product.getLargeInStock()-(quantity-prev));
            }
            else{
                  int x=product.getLargeInStock()+prev;
                  return "redirect:/cart/" + String.valueOf(customerId)+"?q=Only+"+x+"+left!!";
            }
        }
        model.addAttribute( "user",securityservices.findLoggedInCustomer());
        List<Product> cartItems = cartItemRepository.getCartitemasProduct(customerId);
        model.addAttribute("cartItems", cartItems);
        return "redirect:/cart/" + String.valueOf(customerId);
    }

    @PostMapping("/delete-from-cart")
    public String deletefromCart(@RequestParam("customerId") int customerId,@RequestParam("id")int id,@RequestParam("size")String size,@RequestParam("prev")int prev, Model model){
        Product product= productRepository.getProductById(id);
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
        if(cartItems.isEmpty()){
            return "redirect:/cart/" + String.valueOf(customerId)+"?f=CartEmpty";
        }
        return "redirect:/cart/"+String.valueOf(customerId);
    }

    @PostMapping("/addAddress")
    public String addAddress(@RequestParam Map<String, String> body, Model model) {
        Customer customer = customerRepository.getCustomerbyUsername(securityservices.findLoggedInUsername());
        customerAddressRepository.addAddress(
                customer.getCustomerId(),
                body.get("street"),
                body.get("city"),
                body.get("postalCode"),
                body.get("country")
        );
        return "redirect:/cart/"+ Integer.toString(customer.getCustomerId());
    }

    @PostMapping("/addPhoneNumber")
    public String AddPhoneNumber(@RequestParam Map<String,String> body,Model model){
        Customer customer=customerRepository.getCustomerbyusername(securityservices.findLoggedInUsername());
        customerPhoneRepository.createNewCustomerPhone(
                customer.getCustomerId(),
                body.get("phoneNumber")
        );
        return "redirect:/cart/" + Integer.toString((customer.getCustomerId()));
    }
}
