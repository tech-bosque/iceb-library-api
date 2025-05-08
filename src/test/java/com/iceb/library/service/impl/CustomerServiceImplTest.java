package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.CustomerNotFoundException;
import com.iceb.library.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void createCustomerTest() {
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(TestUtils.customer(false));

        CustomerRequestDto customerRequestDto = TestUtils.customerRequestDto();

        CustomerResponseDto customerResponseDto = customerServiceImpl.createCustomer(customerRequestDto);

        assertThat(customerResponseDto).usingRecursiveComparison().ignoringFields("id","archived").isEqualTo(customerRequestDto);
    }

    @Test
    void getCustomerByIdTest() {
        Customer customer = TestUtils.customer(false);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        CustomerResponseDto customerResponseDto = customerServiceImpl.getCustomerById(customer.getId());

        assertThat(customer).usingRecursiveComparison().ignoringFields("password").isEqualTo(customerResponseDto);
    }

    @Test
    void getCustomerIdNotFoundTest() {
        UUID customerId = UUID.randomUUID();

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            customerServiceImpl.getCustomerById(customerId);
        });
    }

    @Test
    void searchCustomersTest() {
        List<Customer> customers = List.of(TestUtils.customer(false), TestUtils.customer(true));

        when(customerRepository.searchCustomers(TestUtils.customerSearchDto())).thenReturn(customers);

        List<CustomerResponseDto> customerResponseDtos = customerServiceImpl.searchCustomers(TestUtils.customerSearchDto());

        Assertions.assertEquals(2, customerResponseDtos.size());
        assertThat(customers).usingRecursiveComparison().ignoringFields("password").isEqualTo(customerResponseDtos);
    }

    @Test
    void updateCustomerTest() {
        Customer customer = TestUtils.customer(false);
        CustomerRequestDto customerRequestDto = TestUtils.customerRequestDto();

        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerResponseDto customerResponseDto = customerServiceImpl.updateCustomer(customer.getId(), customerRequestDto);

        assertThat(customerResponseDto).usingRecursiveComparison().ignoringFields("id","archived").isEqualTo(customerRequestDto);
    }

    @Test
    void deleteCustomerTest() {
        Customer customer = TestUtils.customer(false);

        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        customerServiceImpl.deleteCustomer(customer.getId());

        CustomerResponseDto customerResponseDto = customerServiceImpl.deleteCustomer(customer.getId());

        Assertions.assertEquals(customer.getName(), customerResponseDto.getName());
        Assertions.assertTrue(customerResponseDto.getArchived());
    }
}
