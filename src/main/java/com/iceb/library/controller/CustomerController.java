package com.iceb.library.controller;

import com.iceb.library.dto.customer.CustomerEmailUpdateDto;
import com.iceb.library.dto.customer.CustomerPasswordUpdateDto;
import com.iceb.library.dto.customer.CustomerPhoneUpdateDto;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerSearchDto;
import com.iceb.library.dto.customer.CustomerUpdateDto;
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
    @Operation(summary = "Update a customer's name and role")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable UUID id, @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomer(id, customerUpdateDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/{id}/email")
    @Operation(summary = "Update a customer's email")
    public ResponseEntity<CustomerResponseDto> updateCustomerEmail(@PathVariable UUID id, @Valid @RequestBody CustomerEmailUpdateDto customerEmailUpdateDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomerEmail(id, customerEmailUpdateDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/{id}/phone")
    @Operation(summary = "Update a customer's phone")
    public ResponseEntity<CustomerResponseDto> updateCustomerPhone(@PathVariable UUID id, @Valid @RequestBody CustomerPhoneUpdateDto customerPhoneUpdateDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomerPhone(id, customerPhoneUpdateDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/{id}/password")
    @Operation(summary = "Update a customer's password")
    public ResponseEntity<CustomerResponseDto> updateCustomerPassword(@PathVariable UUID id, @Valid @RequestBody CustomerPasswordUpdateDto customerPasswordUpdateDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomerPassword(id, customerPasswordUpdateDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer")
    public ResponseEntity<CustomerResponseDto> deleteCustomer(@PathVariable UUID id) {
        CustomerResponseDto archivedCustomer = customerService.deleteCustomer(id);
        return ResponseEntity.ok(archivedCustomer);
    }
}
