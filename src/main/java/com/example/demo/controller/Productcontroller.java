package com.example.demo.controller;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class Productcontroller {
    private final ProductRepository productRepository;

    @Autowired
    public Productcontroller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/Product")
    public String setProduct(Product product){
        productRepository.createProduct(product);
        return "Product";
    }

    @PutMapping("/Product")
    public String updateProduct(Product product){
        productRepository.updateProduct(product);
        return "update ayyindi amma next!!";
    }

}
