package com.example.demo;

import com.example.demo.dao.ProductRepository;
import com.example.demo.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlapsApplication implements CommandLineRunner {

	private ProductRepository productRepository;
	private Product product;

	public BlapsApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlapsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("running");
//		product=new Product(2,2,"nirma","saaf saaf peela peela",50,6);
//		productRepository.createProduct(product);
//		System.out.println(product.getDescription());
	}
}
