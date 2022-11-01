package com.example.demo.controller;

import com.example.demo.dao.CustomerRepository;
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

@Controller
public class Customercontroller {

    @Autowired
    private SecurityServices securityservices;
    @Autowired
    private CustomerRepository customerRepository;

//    @RequestMapping(path="/index",method= RequestMethod.GET)
//    public String indexduhh(){
//        System.out.println("sss");return "indexform";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
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
        if(customerRepository.getCustomerbyUsername(customer.getUserName()) != null)
        {
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
        model.addAttribute("naam",securityservices.findLoggedInUsername());
        return "login";
    }

    @RequestMapping(value="/logou", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response,Model model) {
        model.addAttribute("name",securityservices.findLoggedInUsername());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @GetMapping("/")
    public String welcome() {
        System.out.println("%%%%%");
        return "home";
    }

    @RequestMapping("/logout-success")
    public String logo(){
        return "logout";
    }

    @GetMapping("/welcome")
    public String welcoe() {
        System.out.println("%%%%%");
        return "logout";
    }


}
