package com.example.demo.dao;

import com.example.demo.models.CartItem;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartItemRepository {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<CartItem> getCartItemsBycustomerId(int customerId) {
        String sql = "SELECT * FROM CartItem WHERE customerId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), new Object[] { customerId });
    }

    public void addToCart(Customer customer, int productId, String size) {
        Product product = productRepository.getProductById(productId);
        String sql_query = "INSERT INTO CartItem (customerId, productId, quantity, size, unitPrice) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_query,
                customer.getCustomerId(),
                product.getProductId(),
                1,
                size,
                product.getUnitPrice()
        );
    }

}