package com.iceb.library.controller;

import com.iceb.library.dto.CustomerRequestDto;
import com.iceb.library.dto.CustomerResponseDto;
import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer Management", description = "APIs for managing Customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto createdCustomer = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable UUID id) {
        CustomerResponseDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/search")
    @Operation(summary = "Get all customers or search with filters")
    public ResponseEntity<List<CustomerResponseDto>> searchCustomers(@RequestBody @Valid CustomerSearchDto customerSearchDto) {
        List<CustomerResponseDto> customers = customerService.searchCustomers(customerSearchDto);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable UUID id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomer(id, customerRequestDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer")
    public ResponseEntity<CustomerResponseDto> deleteCustomer(@PathVariable UUID id) {
        CustomerResponseDto archivedCustomer = customerService.deleteCustomer(id);
        return ResponseEntity.ok(archivedCustomer);
    }
}