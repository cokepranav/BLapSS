package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.Customer;
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

	public List<Product> search(String keyword ){
//		keyword = "%"+keyword+"%";
		String sql_query = "select * from Product where match(title, description) against(?)";
		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { keyword } );
	}

	public void createProduct(Product product) {
		String sql_query = "INSERT INTO Product (categoryId, title, imageLink, description, unitPrice, smallInStock, mediumInStock, largeInStock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
		System.out.println(product.getProductId());
		String sql_query = "UPDATE Product SET categoryId = ?, title = ?, imageLink = ?, description = ?, unitPrice = ?, smallInStock = ?, mediumInStock = ?, largeInStock = ? WHERE productId = ?";
		jdbcTemplate.update(sql_query,
				product.getCategoryId(),
				product.getTitle(),
				product.getImageLink(),
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

	public List<Product> productbyCategory(int categoryId){
		String sql_query = "SELECT * FROM Product where categoryId=?";
		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { categoryId });
	}

	public List<Product> sortbyINVS(){
		String sql_query = "SELECT * FROM Product where categoryId=1 order by (smallInStock+mediumInStock+largeInStock) limit 4";
		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class));

	}

	public List<Product> sortbyINVP(){
		String sql_query = "SELECT * FROM Product where categoryId=2 order by (smallInStock+mediumInStock+largeInStock) limit 4";
		return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class));

	}


	public Product getProductById(int productId) {
			String sql_query = "SELECT * FROM Product WHERE productId = ?";
			return jdbcTemplate.queryForObject(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { productId });
	}

	public void intoCart(int productId,String size){

		if(size.equals("S")){
			String sql_query="Update product set smallInStock=smallInStock-1 where productId=?";
			jdbcTemplate.update(sql_query,productId);
		}
		if(size.equals("M")){
			String sql_query="Update product set mediumInStock=mediumInStock-1 where productId=?";
			jdbcTemplate.update(sql_query,productId);
		}
		if(size.equals("L")){
			String sql_query="Update product set largeInStock=largeInStock-1 where productId=?";
			jdbcTemplate.update(sql_query,productId);
		}
	}

	public void intoCartUpdate(int productId,String size,int quantity){

		if(size.equals("S")){
			String sql_query="Update product set smallInStock=? where productId=?";
			jdbcTemplate.update(sql_query,quantity,productId);
		}
		if(size.equals("M")){
			String sql_query="Update product set mediumInStock=? where productId=?";
			jdbcTemplate.update(sql_query,quantity,productId);
		}
		if(size.equals("L")){
			String sql_query="Update product set largeInStock=? where productId=?";
			jdbcTemplate.update(sql_query,quantity,productId);
		}
	}
}