package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Productcontroller {
    @Autowired
    private ProductRepository productRepository;

//    @GetMapping("/SaveProduct")
    @RequestMapping(path="/SaveProduct",method={RequestMethod.POST, RequestMethod.GET})
    public String setProduct(@ModelAttribute Product product){
//        String ef=request.getParameter("title");
//        System.out.println(ef);
        productRepository.createProduct(product);
//        model.addAttribute("newProduct",product);
        return "Productform";
    }

    @PutMapping("/Product")
    public String updateProduct(@RequestBody Product product){
        productRepository.updateProduct(product);
        return "Product";
    }

    @DeleteMapping("/Product")
    public String deleteproduct(@RequestBody Product product){
        productRepository.deleteProduct(product);
        return "Product";
    }

    @GetMapping("/Products")
//    @RequestMapping()
    public String getALlpers(Model model){
        model.addAttribute("products", productRepository.getAll());
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("Product");
//        modelAndView.addObject("products",p);
//        System.out.println(p);
//        return modelAndView;
        return "Product";
    }
    @GetMapping("/Product/{id}")
    public String getallproducts(@PathVariable("id")int id,Model model){
//        Product p=productRepository.getProductById(id);
        model.addAttribute("products", productRepository.getProductById(id));
//        System.out.println(p);
        return "Product";
    }

}
