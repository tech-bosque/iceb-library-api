package com.iceb.library.service.impl;

import com.iceb.library.entity.Customer;
import com.iceb.library.service.CustomerService;
import com.iceb.library.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerService customerService;

    public Optional<Customer> findByEmail(String email) {
        return customerService.findByEmail(email);
    }
}
