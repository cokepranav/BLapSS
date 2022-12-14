package com.example.demo.controller;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.models.Category;
import com.example.demo.models.Customer;
import com.example.demo.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Customercontroller {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private SecurityServices securityservices;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        model.addAttribute("naam",securityservices.findLoggedInUsername());
        model.addAttribute("customer", new Customer());
        model.addAttribute("error_msg", "");
        return "signup";
    }

    @GetMapping("/loginreg")
    public String logireg(){
        return "logi";
    }

    @RequestMapping(path="/signup",method=RequestMethod.POST)
    public String makeit(Customer customer, Model model){
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        if(customerRepository.getCustomerbyUsername(customer.getUserName()) != null)
        {
            model.addAttribute("user",securityservices.findLoggedInCustomer());
            model.addAttribute("naam",securityservices.findLoggedInUsername());
            model.addAttribute("customer", new Customer());
            model.addAttribute("error_msg", "Username already exists!!");
            return "signup";
        }
        customerRepository.addCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getUserName(), customer.getPassword(),"user");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String logi(Model model){
        List<Category> categories=categoryRepository.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        model.addAttribute("naam",securityservices.findLoggedInUsername());
        return "login";
    }

    @RequestMapping(value="/logou", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response,Model model) {
        model.addAttribute("user",securityservices.findLoggedInCustomer());
        model.addAttribute("name",securityservices.findLoggedInUsername());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping("/logout-success")
    public String logo(){
        return "logout";
    }


}
