package com.iceb.library.service;

import com.iceb.library.entity.Customer;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerSearchDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto topicRequestDto);
    CustomerResponseDto getCustomerById(UUID id);
    List<CustomerResponseDto> searchCustomers(CustomerSearchDto customerSearchDto);
    CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto topicRequestDto);
    CustomerResponseDto deleteCustomer(UUID id);
    Optional<Customer> findByEmail(String email);
}
