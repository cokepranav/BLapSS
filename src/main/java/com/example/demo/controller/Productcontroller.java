package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Productcontroller {
    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/Product")
    public String setProduct(@RequestBody Product product){
        productRepository.createProduct(product);
        return "Product";
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
    public String getALlpers(Model model){
        model.addAttribute("prouducts", productRepository.getAll());
        List<Product> p=productRepository.getAll();
        System.out.println(p);
        return "product";
    }
    @GetMapping("/Product/{id}")
    public String getallproducts(@PathVariable("id")int id){
        Product p=productRepository.getProductById(id);
        System.out.println(p);
        return "Product";
    }

}
