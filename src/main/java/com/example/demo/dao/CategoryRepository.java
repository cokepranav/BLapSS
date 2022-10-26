package com.example.demo.dao;

import com.example.demo.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createCategory(Category category) {
        String sql_query = "INSERT INTO Category (categoryId,name) VALUES (?, ?)";
        jdbcTemplate.update(sql_query,
                category.getCategoryId(),
                category.getName()

        );
    }

    public void updateCategory(Category category) {
        String sql_query = "UPDATE Category SET categoryId = ? WHERE name = ?";
        jdbcTemplate.update(sql_query,
                category.getCategoryId(),
                category.getName()
        );
    }

    public void deleteCategory(Category category) {
        String sql = "DELETE FROM CartItem WHERE categoryId = ?";
        jdbcTemplate.update(sql,
                category.getCategoryId()
        );

    }

    public List<Category> getCategoryByCartId(String cartId) {
        String sql = "SELECT * FROM Cart WHERE cartID=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), new Object[]{cartId});
    }

}
