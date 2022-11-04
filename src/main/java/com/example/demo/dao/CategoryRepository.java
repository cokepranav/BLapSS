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
        String sql_query = "INSERT INTO Category (name) VALUES (?)";
        jdbcTemplate.update(sql_query,
                category.getTitle()
        );
    }

    public void updateCategory(Category category) {
        String sql_query = "UPDATE Category SET name = ? WHERE categoryId = ?";
        jdbcTemplate.update(sql_query,
                category.getTitle(),
                category.getCategoryId()
        );
    }

    public void deleteCategory(Category category) {
        String sql = "DELETE FROM Category WHERE categoryId = ?";
        jdbcTemplate.update(sql,
                category.getCategoryId()
        );

    }

    public List<Category> getCategories() {
        String sql = "SELECT * FROM Category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    public List<Category> getCategory(int categoryId) {
        String sql = "SELECT * FROM Category WHERE categoryId=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), new Object[]{categoryId});
    }

}