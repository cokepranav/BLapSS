package com.example.demo.dao;

import com.example.demo.models.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createCartItem(CartItem cartItem) {
        String sql_query = "INSERT INTO cartItem (cartId, productId,quantity,unitPrice) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql_query,
                cartItem.getCartId(),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice()
        );
    }

    public void updateCartItem(CartItem cartItem) {
        String sql_query = "UPDATE cartItem SET productId = ? WHERE cartId = ?";
        jdbcTemplate.update(sql_query,
                cartItem.getCartId(),
                cartItem.getProductId()
        );
    }

    public void deleteCartItem(CartItem cartItem) {
        String sql = "DELETE FROM CartItem WHERE cartId = ?";
        jdbcTemplate.update(sql,
                cartItem.getCartId()
        );

    }

    public List<CartItem> getCartItemByCartId(String cartId) {
        String sql = "SELECT * FROM Cart WHERE cartID=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), new Object[]{cartId});
    }

}
