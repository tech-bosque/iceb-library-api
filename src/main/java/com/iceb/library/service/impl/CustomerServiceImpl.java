package com.iceb.library.service.impl;

import com.iceb.library.dto.CustomerResponseDto;
import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.dto.CustomerRequestDto;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.CustomerNotFoundException;
import com.iceb.library.repository.CustomerRepository;
import com.iceb.library.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        logger.info("Creating customer");
        logger.debug("Creating customer with details: {}", customerRequestDto);

        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .phone(customerRequestDto.getPhone())
                .email(customerRequestDto.getEmail())
                .password(customerRequestDto.getPassword())
                .role(customerRequestDto.getRole())
                .archived(false)
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        logger.info("Created customer successfully");
        logger.debug("Created customer: {}", savedCustomer);

        return mapToResponseDto(savedCustomer, Arrays.asList(false));
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID id) {
        logger.info("Fetching customer by ID");
        logger.debug("Fetching customer with ID: {}", id);

        Customer customer = findCustomerById(id);
        CustomerResponseDto responseDto = mapToResponseDto(customer, Arrays.asList(false));

        logger.debug("Fetched customer: {}", responseDto);
        logger.info("Fetched customer by ID successfully");
        return responseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto customerRequestDto) {
        logger.info("Updating customer");
        logger.debug("Updating customer with ID: {} with details: {}", id, customerRequestDto);

        Customer existingCustomer = findCustomerById(id);
        existingCustomer.setName(customerRequestDto.getName());
        existingCustomer.setEmail(customerRequestDto.getEmail());
        existingCustomer.setPhone(customerRequestDto.getPhone());
        existingCustomer.setRole(customerRequestDto.getRole());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        logger.info("Updated customer successfully");
        logger.debug("Updated customer: {}", updatedCustomer);

        return mapToResponseDto(updatedCustomer, Arrays.asList(false));
    }

    @Override
    public CustomerResponseDto deleteCustomer(UUID id) {
        logger.info("Archiving customer");
        logger.debug("Archiving customer with ID: {}", id);

        Customer customer = findCustomerById(id);
        customer.setArchived(true);
        Customer archivedCustomer = customerRepository.save(customer);

        logger.info("Archived customer successfully");
        logger.debug("Archived customer with ID: {}", id);

        return mapToResponseDto(archivedCustomer, Arrays.asList(false));
    }

    @Override
    public List<CustomerResponseDto> searchCustomers(CustomerSearchDto customerSearchDto) {
        logger.info("Fetching customers with filters");
        logger.debug("Fetching customers with filters: {}", customerSearchDto);

        List<Customer> customers = customerRepository.searchCustomers(customerSearchDto);

        List<CustomerResponseDto> responseDtos = customers.stream()
                .map(customer -> mapToResponseDto(customer, customerSearchDto.getArchived()))
                .collect(Collectors.toList());

        logger.debug("Fetched customers: {}", responseDtos);
        logger.info("Fetched customers with filters successfully");
        return responseDtos;
    }

    private Customer findCustomerById(UUID id) {
        logger.info("Finding customer by ID");
        logger.debug("Finding customer with ID: {}", id);

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with the provided ID does not exist"));
    }

    private CustomerResponseDto mapToResponseDto(Customer customer, List<Boolean> archived) {
        CustomerResponseDto.CustomerResponseDtoBuilder builder = CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .role(customer.getRole());

        if (archived.contains(true)) {
            builder.archived(customer.getArchived());
        }

        return builder.build();
    }
}
