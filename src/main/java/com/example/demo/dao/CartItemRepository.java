package com.example.demo.dao;

import com.example.demo.models.CartItem;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartItemRepository {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<CartItem> getCartItemsBycustomerId(int customerId) {
        String sql = "SELECT * FROM CartItem WHERE customerId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), new Object[] { customerId });
    }

    public void addToCart(Customer customer, int productId, String size) {
        Product product = productRepository.getProductById(productId);
        String sql_query = "INSERT INTO CartItem (customerId, productId, quantity, size, unitPrice) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_query,
                customer.getCustomerId(),
                product.getProductId(),
                1,
                size,
                product.getUnitPrice()
        );
    }

    public CartItem gethiscart(int customerId,int productId,String size){
        try{
        String sql_query="select * from CartItem where customerId=? and productId=? and size=?";
        return jdbcTemplate.queryForObject(sql_query,new BeanPropertyRowMapper<>(CartItem.class), new Object[] { customerId,productId,size });
    } catch (EmptyResultDataAccessException e) {
        return null;
    }

    }

    public List<Product> getCartitemasProduct(int id){
        String sql_query="select productId,customerId as categoryId,title,imageLink,size as description,unitPrice,quantity as smallInStock,mediumInStock,largeInStock from cartitem natural join product where customerId=?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(Product.class), new Object[] { id });
    }

    public void updateCartItem(int customerId,int productId,int quantity,String size){
        String sql_query="update cartitem set quantity=? where customerId=? and productId=? and size=?";
        jdbcTemplate.update(sql_query,quantity,customerId,productId,size);
    }

    public void  deleteCartItem(int customerId,int productId,String size){
        String sql_query="delete from cartitem where customerId=? and productId=? and size=?";
        jdbcTemplate.update(sql_query,customerId,productId,size);
    }
    public void  deleteCartItemofCustomer(int customerId){
        String sql_query="delete from cartitem where customerId=?";
        jdbcTemplate.update(sql_query,customerId);
    }
    public List<CartItem> getTotal(int customerId){
        String sql_query="select * from cartitem where customerId=?";
        return jdbcTemplate.query(sql_query, new BeanPropertyRowMapper<>(CartItem.class), new Object[] { customerId });
    }
}