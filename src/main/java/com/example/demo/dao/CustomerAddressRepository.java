package com.example.demo.dao;

import com.example.demo.models.Cart;
import com.example.demo.models.Category;
import com.example.demo.models.CustomerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerAddressRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void insertAddressFromCustId(CustomerAddress customerAddress) {
        String sql_query = "INSERT INTO CustomerAddress (street,city,country,postalcode) values( ?,?,?,?)";
        jdbcTemplate.update(sql_query,
                customerAddress.getStreet(),
                customerAddress.getCity(),
                customerAddress.getCountry(),
                customerAddress.getPostalcode()
        );
    }
    public void addAddress(int customerId, String street,String city,String postalcode,String country){
        String sql_query = "INSERT INTO CustomerAddress (customerId, street, city, country, postalcode) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_query, customerId,street,city,country,postalcode);
    }

    public List<CustomerAddress> getAddressBycustId (int CustomerId) {
        String sql="Select * from CustomerAddress where customerId=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CustomerAddress.class), new Object[]{CustomerId});
    }
}