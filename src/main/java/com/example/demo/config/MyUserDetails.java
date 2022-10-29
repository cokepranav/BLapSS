package com.example.demo.config;

import com.example.demo.models.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserDetails implements UserDetails {

    //    private static final long serialVersionUID = 1L;
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));

//        List<GrantedAuthority> grantList = new ArrayList<>();
//        if (user == null) {
//            return grantList;
//        }
//        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole());
//        grantList.add(authority);
//        return grantList;
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public MyUserDetails(Customer customer) {
        this.customer = customer;
    }

    public Customer getUser() {
        return this.customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

}