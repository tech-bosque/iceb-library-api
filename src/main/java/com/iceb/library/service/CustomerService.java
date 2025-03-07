package com.iceb.library.service;

import com.iceb.library.dto.CustomerRequestDto;
import com.iceb.library.dto.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto topicRequestDto);
    CustomerResponseDto getCustomerById(UUID id);
    List<CustomerResponseDto> searchCustomers(String name, boolean archived);
    CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto topicRequestDto);
    CustomerResponseDto deleteCustomer(UUID id);
}
