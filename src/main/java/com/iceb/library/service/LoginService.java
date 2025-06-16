package com.iceb.library.service;

import com.iceb.library.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LoginService {
    Optional<Customer> findByEmail(String email);
}
