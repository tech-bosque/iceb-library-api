package com.iceb.library.service;

import com.iceb.library.entity.Customer;

import java.util.Optional;

public interface LoginService {
    Optional<Customer> findByEmail(String email);
}
