package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Repository
public class ProductRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void createProduct(Product product) {
		String sql_query = "INSERT INTO Product (categoryId, title, imageLink, description, unitPrice, smallInStock, mediumInStock, largeInStock,) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql_query,
				product.getCategoryId(),
				product.getTitle(),
				product.getImageLink(),
				product.getDescription(),
				product.getUnitPrice(),
				product.getSmallInStock(),
				product.getMediumInStock(),
				product.getLargeInStock()
		);
	}

	public void updateProduct(Product product) {
		String sql_query = "UPDATE Product SET categoryId = ?, title = ?, description = ?, unitPrice = ?, inventory = ? WHERE productId = ?";
		jdbcTemplate.update(sql_query,
				product.getCategoryId(),
				product.getTitle(),
				product.getDescription(),
				product.getUnitPrice(),
				product.getSmallInStock(),
				product.getMediumInStock(),
				product.getLargeInStock(),
				product.getProductId()
		);
	}

	public void deleteProduct(Product product) {
		String sql_query = "DELETE FROM Product WHERE productId = ?";
		jdbcTemplate.update(sql_query,
				product.getProductId()
		);
	}

	public List<Product> getAll() {
		String sql_query = "SELECT * FROM Product";
		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class));
	}

	public Product getProductById(int productId) {
		try {
			String sql_query = "SELECT * FROM Product WHERE productId = ?";
			return jdbcTemplate.queryForObject(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { productId });
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}

}