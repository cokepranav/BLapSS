package com.example.demo.dao;

import com.example.demo.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createCart(Cart cart) {
        String sql_query = "INSERT INTO cart (cartId, createdDate) VALUES (?, ?)";
        jdbcTemplate.update(sql_query,
                cart.getCartId(),
                cart.getCreatedDate()
        );
    }

    public void updateCart(Cart cart) {
        String sql_query = "UPDATE cart SET createdDate = ? WHERE cartId = ?";
        jdbcTemplate.update(sql_query,
                cart.getCartId(),
                cart.getCreatedDate()
        );
    }

    public void deleteCart(Cart cart) {
        String sql = "DELETE FROM Cart WHERE cartId = ?";
        jdbcTemplate.update(sql,
                cart.getCartId()
        );

    }

    public List<Cart> getCartBycartId(String cartId) {
        String sql = "SELECT * FROM Cart WHERE cartID=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class), new Object[]{cartId});
    }
}
