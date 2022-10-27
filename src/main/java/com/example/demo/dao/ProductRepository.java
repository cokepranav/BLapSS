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
	private JdbcTemplate jdbcTemplate;

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

	public void deleteProduct(Product product) {
		String sql_query = "DELETE FROM Product WHERE productId = ?";
		jdbcTemplate.update(sql_query,
				product.getProductId()
		);
	}

	public List<Product> getAll() {
		String sql_query = "SELECT * FROM Product";
		System.out.println(sql_query);
		return jdbcTemplate.query(sql_query, BeanPropertyRowMapper.newInstance(Product.class),new Object[]{});
//		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class));
//		return jdbcTemplate.query(sql_query,(rs,rowNum)->{return new Product(rs.getInt("productId"),rs.getInt("categoryId"),rs.getString("title"),rs.getString("description"),rs.getFloat("unitPrice"),rs.getInt("inventory"));});
	}

	public Product getProductById(int productId) {
		try {
			String sql_query = "SELECT * FROM Product WHERE productId = ?";
			return jdbcTemplate.queryForObject(sql_query, BeanPropertyRowMapper.newInstance(Product.class), new Object[] { productId });
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}

}
