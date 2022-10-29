package com.example.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.MyUserDetails;
import com.example.demo.models.Customer;

@Transactional
@Service
public class SecurityServices {

    public String findLoggedInUsername() {
        Object myCustomerDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (myCustomerDetails instanceof MyUserDetails) {
            return ((MyUserDetails) myCustomerDetails).getUser().getUserName();
        }

        return null;
    }

    public Customer findLoggedInCustomer() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (myUserDetails instanceof MyUserDetails) {
            Customer customer = ((MyUserDetails) myUserDetails).getUser();
            return customer;
        }
        return null;
    }

    public void autoLogout() {
        SecurityContextHolder.clearContext();
    }
}