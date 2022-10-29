package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CustomerPhoneNumber;

@Repository
public class CustomerPhoneRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createCustomerPhone(CustomerPhoneNumber customerPhone) {
        String sql_query = "INSERT INTO CustomerPhoneNumber (customerId, phoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(sql_query,
                customerPhone.getCustomerId(),
                customerPhone.getPhoneNumber()
        );
    }

    public void updateCustomerPhone(CustomerPhoneNumber customerPhone) {
        String sql_query = "UPDATE CustomerPhoneNumber SET phoneNumber = ? WHERE customerId = ?";
        jdbcTemplate.update(sql_query,
                customerPhone.getPhoneNumber(),
                customerPhone.getCustomerId()
        );
    }

    public void deleteCustomerPhone(CustomerPhoneNumber customerPhone) {
        String sql_query = "DELETE FROM CustomerPhoneNumber WHERE phoneNumber = ?";
        jdbcTemplate.update(sql_query,
                customerPhone.getPhoneNumber()
        );
    }

    public List<CustomerPhoneNumber> getAllPhonesByCustomerId(int customerId) {
        String sql_query = "SELECT * FROM CustomerPhoneNumber WHERE customerId = ?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(CustomerPhoneNumber.class), new Object[] { customerId });
    }

}