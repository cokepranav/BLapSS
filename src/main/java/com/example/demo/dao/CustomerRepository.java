package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.geo.CustomMetric;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;

@Repository
public class CustomerRepository {

    private Customer customer;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getFullName() {
        return this.customer.getFirstName()+this.customer.getLastName();
    }

    public Customer getCustomerbyEmail(String email) {

        try {
            String sqlst = "SELECT * FROM customer WHERE emailAddress = ?";
            return jdbcTemplate.queryForObject(sqlst, BeanPropertyRowMapper.newInstance(Customer.class), new Object[] { email });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addCustomer(String firstName,String lastName,String emailAddress,String userName,String password,String role){
        password=bCryptPasswordEncoder.encode(password);
        String sql_query = "INSERT INTO Customer (firstName, lastName, emailAddress, userName, password, role) VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql_query,firstName,lastName,emailAddress,userName,password,role);
    }

    public Customer getCustomerbyUsername(String userName){

        try {
            String sqlst = "SELECT * FROM Customer WHERE userName = ?";
            return jdbcTemplate.queryForObject(sqlst, BeanPropertyRowMapper.newInstance(Customer.class), new Object[] { userName });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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


    public void updateEmail(Customer customer){
        String sql_query = "Update Customer SET  emailAddress = ? WHERE customerID = ?";
        jdbcTemplate.update(sql_query,
                customer.getEmailAddress(),
                customer.getCustomerId()
        );
    }


    public Customer getCustomerbyusername(String username) {
        try {
            String sqlst = "SELECT * FROM customer WHERE userName = ?";
            return jdbcTemplate.queryForObject(sqlst, BeanPropertyRowMapper.newInstance(Customer.class), new Object[] { username });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}