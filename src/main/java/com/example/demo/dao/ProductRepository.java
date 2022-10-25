package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Repository
public class ProductRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void createProduct(Product product) {
		String sql_query = "INSERT INTO Product (productId, categoryId, title, description, unitPrice, inventory) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql_query, 
				product.getProductId(), 
				product.getCategoryId(), 
				product.getTitle(), 
				product.getDescription(), 
				product.getUnitPrice(), 
				product.getInventory()
		);
	}
	
	public void updateProduct(Product product) {
		String sql_query = "UPDATE Product SET categoryId = ?, title = ?, description = ?, unitPrice = ?, inventory = ? WHERE productId = ?";
		jdbcTemplate.update(sql_query,
				product.getCategoryId(),
				product.getTitle(),
				product.getDescription(),
				product.getUnitPrice(),
				product.getInventory(),
				product.getProductId()
		);
	}
	
	
	
}
