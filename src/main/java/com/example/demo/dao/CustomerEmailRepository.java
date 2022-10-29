package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CustomerEmail;

@Repository
public class CustomerEmailRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createCustomerEmail(CustomerEmail customerEmail) {
        String sql_query = "INSERT INTO CustomerEmail (customerId, emailAddress) VALUES (?, ?)";
        jdbcTemplate.update(sql_query,
                customerEmail.getCustomerId(),
                customerEmail.getEmailAddress()
        );
    }

    public void updateCustomerEmail(CustomerEmail customerEmail) {
        String sql_query = "UPDATE CustomerEmail SET emailAddress = ? WHERE customerId = ?";
        jdbcTemplate.update(sql_query,
                customerEmail.getEmailAddress(),
                customerEmail.getCustomerId()
        );
    }

    public void deleteCustomerEmail(CustomerEmail customerEmail) {
        String sql_query = "DELETE FROM CustomerEmail WHERE emailAddress = ?";
        jdbcTemplate.update(sql_query,
                customerEmail.getEmailAddress()
        );
    }

    public List<CustomerEmail> getAllEmailsByCustomerId(int customerId) {
        String sql_query = "SELECT * FROM CustomerEmail WHERE customerId = ?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(CustomerEmail.class), new Object[] { customerId });
    }

}