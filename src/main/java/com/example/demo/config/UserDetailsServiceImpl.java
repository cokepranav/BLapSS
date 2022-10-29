package com.example.demo.config;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Lazy
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public com.example.demo.config.MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.getCustomerbyusername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Invalid username");
        }

        // Need to update timeStamp
        return new com.example.demo.config.MyUserDetails(customer);
    }

}