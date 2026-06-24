package com.iceb.library.service.impl;

import com.iceb.library.dto.customer.CustomerEmailUpdateDto;
import com.iceb.library.dto.customer.CustomerPhoneUpdateDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerSearchDto;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerUpdateDto;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.CustomerAlreadyExistsException;
import com.iceb.library.exception.CustomerNotFoundException;
import com.iceb.library.exception.InvalidCustomerEmailException;
import com.iceb.library.repository.CustomerRepository;
import com.iceb.library.service.CustomerService;
import com.iceb.library.utils.TranslatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        customerRepository.findByEmail(customerRequestDto.getEmail())
                .ifPresent(existing -> {
                    throw new CustomerAlreadyExistsException("A customer with this email already exists.");
                });

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(customerRequestDto.getPassword());
        customerRequestDto.setPassword(encryptedPassword);

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

        return TranslatorUtils.customerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID id) {
        logger.info("Fetching customer by ID");
        logger.debug("Fetching customer with ID: {}", id);

        Customer customer = findCustomerById(id);
        CustomerResponseDto responseDto = TranslatorUtils.customerToCustomerResponseDto(customer);

        logger.debug("Fetched customer: {}", responseDto);
        logger.info("Fetched customer by ID successfully");
        return responseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(UUID id, CustomerUpdateDto customerUpdateDto) {
        logger.info("Updating customer");
        logger.debug("Updating customer with ID: {} with details: {}", id, customerUpdateDto);

        Customer existingCustomer = findCustomerById(id);
        existingCustomer.setName(customerUpdateDto.getName());
        existingCustomer.setRole(customerUpdateDto.getRole());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        logger.info("Updated customer successfully");
        logger.debug("Updated customer: {}", updatedCustomer);

        return TranslatorUtils.customerToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto updateCustomerEmail(UUID id, CustomerEmailUpdateDto customerEmailUpdateDto) {
        logger.info("Updating customer email");
        logger.debug("Updating customer email with ID: {}", id);

        Customer existingCustomer = findCustomerById(id);
        String oldEmail = customerEmailUpdateDto.getOldEmail();
        String newEmail = customerEmailUpdateDto.getNewEmail();

        if (!oldEmail.equals(existingCustomer.getEmail())) {
            throw new InvalidCustomerEmailException("The provided current email does not match.");
        }

        if (!newEmail.equals(existingCustomer.getEmail())) {
            customerRepository.findByEmail(newEmail)
                    .filter(other -> !other.getId().equals(existingCustomer.getId()))
                    .ifPresent(ignored -> {
                        throw new CustomerAlreadyExistsException("A customer with this email already exists.");
                    });
            existingCustomer.setEmail(newEmail);
        }

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        logger.info("Updated customer email successfully");
        return TranslatorUtils.customerToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto updateCustomerPhone(UUID id, CustomerPhoneUpdateDto customerPhoneUpdateDto) {
        logger.info("Updating customer phone");
        logger.debug("Updating customer phone with ID: {}", id);

        Customer existingCustomer = findCustomerById(id);
        existingCustomer.setPhone(customerPhoneUpdateDto.getPhone());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        logger.info("Updated customer phone successfully");
        return TranslatorUtils.customerToCustomerResponseDto(updatedCustomer);
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

        return TranslatorUtils.customerToCustomerResponseDto(archivedCustomer, true);
    }

    @Override
    public List<CustomerResponseDto> searchCustomers(CustomerSearchDto customerSearchDto) {
        logger.info("Fetching customers with filters");
        logger.debug("Fetching customers with filters: {}", customerSearchDto);

        List<Customer> customers = customerRepository.searchCustomers(customerSearchDto);

        List<CustomerResponseDto> responseDtos = customers.stream()
                .map(customer -> TranslatorUtils.customerToCustomerResponseDto(customer, true))
                .collect(Collectors.toList());

        logger.debug("Fetched customers: {}", responseDtos);
        logger.info("Fetched customers with filters successfully");
        return responseDtos;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        logger.info("Finding customer by email");
        logger.debug("Finding customer with email: {}", email);
        return customerRepository.findByEmail(email);
    }

    private Customer findCustomerById(UUID id) {
        logger.info("Finding customer by ID");
        logger.debug("Finding customer with ID: {}", id);

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with the provided ID does not exist"));
    }


}
