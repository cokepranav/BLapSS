package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Review;

@Repository
public class ReviewRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public void createReview(Review review) {
        String sql_query = "INSERT INTO Review (customerId, productId, description, rating) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql_query,
                review.getCustomerId(),
                review.getProductId(),
                review.getDescription(),
                review.getRating()
        );
    }

    public void updateReview(Review review) {
        String sql_query = "UPDATE Review SET description = ?, rating = ? WHERE customerId = ? AND productId = ?";
        jdbcTemplate.update(sql_query,
                review.getDescription(),
                review.getRating(),
                review.getCustomerId(),
                review.getProductId()
        );
    }

    public void deleteReview(Review review) {
        String sql_query = "DELETE FROM Review WHERE customerId = ? AND productId = ?";
        jdbcTemplate.update(sql_query,
                review.getCustomerId(),
                review.getProductId()
        );
    }

    public List<Review> getAll() {
        String sql_query = "SELECT * FROM Reivew";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Review.class));
    }

    public List<Review> getAvgProductReview(int id){
        String sql_query = "SELECT avg(rating) as rating FROM Review where productId=?";
        return jdbcTemplate.query(sql_query,new BeanPropertyRowMapper<>(Review.class), new Object[] { id });

    }

    public List<Review> getProductReview(int id){

            String sql_query = "SELECT * FROM Review where productId=?";
            return jdbcTemplate.query(sql_query,new BeanPropertyRowMapper<>(Review.class), new Object[] { id });

    }

}