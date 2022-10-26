package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addCustomer(Customer customer){
        String sql_query = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql_query,
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getPassword()
        );

    }

    public void updateUserName(Customer customer){
        String sql_query = "Update Customer SET  userName = ?, WHERE customerID = ?";
        jdbcTemplate.update(sql_query,
                customer.getUserName(),
                customer.getCustomerId()
        );
    }

    public void updatePassword(Customer customer){
        String sql_query = "Update Customer SET  password = ? WHERE customerID = ?";
        jdbcTemplate.update(sql_query,
                customer.getPassword(),
                customer.getCustomerId()
        );
    }


}
