package com.example.demo.dao;

import com.example.demo.models.CartItem;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addToorder(int productId,int customerId,int quantity,String size){
        String sql_query="Insert into orderitem (productId,customerId,quantity,size) values (?,?,?,?)";
        jdbcTemplate.update(sql_query,productId,customerId,quantity,size);
    }

    public List<OrderItem> searchinOrder(int productId,int customerId){
        String sql_query="Select * from orderitem where productId=? and customerId=?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(OrderItem.class), new Object[] { productId,customerId });
    }

    public List<OrderItem> allOrders(int customerId){
        String sql_query="Select * from orderitem where customerId=? order by orderId desc";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(OrderItem.class), new Object[] { customerId });
    }

    public List<Product> getOrderitemasProduct(int id){
        String sql_query="select productId,customerId as categoryId,title,imageLink,size as description,orderId as unitPrice,quantity as smallInStock,mediumInStock,largeInStock from orderitem natural join product where customerId=? order by orderId desc";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { id });
    }
}
