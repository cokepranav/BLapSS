package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.CustomerAddress;
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
        String sql_query = "INSERT INTO CustomerPhone (customerId, phoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(sql_query,
                customerPhone.getCustomerId(),
                customerPhone.getPhoneNumber()
        );
    }

    public void updateCustomerPhone(CustomerPhoneNumber customerPhone) {
        String sql_query = "UPDATE CustomerPhone SET phoneNumber = ? WHERE customerId = ?";
        jdbcTemplate.update(sql_query,
                customerPhone.getPhoneNumber(),
                customerPhone.getCustomerId()
        );
    }

    public void deleteCustomerPhone(CustomerPhoneNumber customerPhone) {
        String sql_query = "DELETE FROM CustomerPhone WHERE phoneNumber = ?";
        jdbcTemplate.update(sql_query,
                customerPhone.getPhoneNumber()
        );
    }

    public List<CustomerPhoneNumber> getAllPhonesByCustomerId(int customerId) {
        String sql_query = "SELECT * FROM CustomerPhone WHERE customerId = ?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(CustomerPhoneNumber.class), new Object[] { customerId });
    }

    public void createNewCustomerPhone(int customerId,String phoneNumber) {
        String sql_query = "INSERT INTO CustomerPhone (customerId, phoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(sql_query, customerId, phoneNumber);
    }
    public List<CustomerPhoneNumber> getPhoneBycustId (int CustomerId) {
        String sql="Select * from CustomerPhone where customerId=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CustomerPhoneNumber.class), new Object[]{CustomerId});
    }
}