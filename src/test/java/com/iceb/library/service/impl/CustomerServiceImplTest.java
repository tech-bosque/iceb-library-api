package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.customer.CustomerEmailUpdateDto;
import com.iceb.library.dto.customer.CustomerPhoneUpdateDto;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerUpdateDto;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.CustomerAlreadyExistsException;
import com.iceb.library.exception.CustomerNotFoundException;
import com.iceb.library.exception.InvalidCustomerEmailException;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void createCustomerTest() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(TestUtils.customer(false));

        CustomerRequestDto customerRequestDto = TestUtils.customerRequestDto();

        CustomerResponseDto customerResponseDto = customerServiceImpl.createCustomer(customerRequestDto);

        assertThat(customerResponseDto).usingRecursiveComparison().ignoringFields("id","archived").isEqualTo(customerRequestDto);
    }

    @Test
    void createCustomerWhenEmailAlreadyExistsThrowsException() {
        CustomerRequestDto customerRequestDto = TestUtils.customerRequestDto();
        Customer existingCustomer = TestUtils.customer(false);

        when(customerRepository.findByEmail(customerRequestDto.getEmail())).thenReturn(Optional.of(existingCustomer));

        Assertions.assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerServiceImpl.createCustomer(customerRequestDto);
        });
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
        assertThat(customerResponseDtos.get(0).getEmail()).isEqualTo("t***@e*****.com");
        assertThat(customerResponseDtos.get(0).getPhone()).isEqualTo("******7890");
    }

    @Test
    void updateCustomerTest() {
        Customer customer = TestUtils.customer(false);
        CustomerUpdateDto customerUpdateDto = TestUtils.customerUpdateDto();

        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerResponseDto customerResponseDto = customerServiceImpl.updateCustomer(customer.getId(), customerUpdateDto);

        assertThat(customerResponseDto.getName()).isEqualTo(customerUpdateDto.getName());
        assertThat(customerResponseDto.getRole()).isEqualTo(customerUpdateDto.getRole());
    }

    @Test
    void updateCustomerEmailWhenChangingToExistingEmailThrowsException() {
        Customer existingCustomer = TestUtils.customer(false);
        Customer otherCustomer = TestUtils.customer(false);
        otherCustomer.setId(UUID.randomUUID());
        otherCustomer.setEmail("taken@example.com");

        CustomerEmailUpdateDto customerEmailUpdateDto = CustomerEmailUpdateDto.builder()
                .oldEmail("test@example.com")
                .newEmail("taken@example.com")
                .build();

        when(customerRepository.findById(existingCustomer.getId())).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.findByEmail("taken@example.com")).thenReturn(Optional.of(otherCustomer));

        Assertions.assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerServiceImpl.updateCustomerEmail(existingCustomer.getId(), customerEmailUpdateDto);
        });
    }

    @Test
    void updateCustomerEmailWhenChangingToNewUnusedEmailSucceeds() {
        Customer customer = TestUtils.customer(false);
        CustomerEmailUpdateDto customerEmailUpdateDto = CustomerEmailUpdateDto.builder()
                .oldEmail("test@example.com")
                .newEmail("newemail@example.com")
                .build();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.findByEmail("newemail@example.com")).thenReturn(Optional.empty());
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResponseDto result = customerServiceImpl.updateCustomerEmail(customer.getId(), customerEmailUpdateDto);

        assertThat(result.getEmail()).isEqualTo("newemail@example.com");
    }

    @Test
    void updateCustomerEmailWhenOldEmailDoesNotMatchThrowsException() {
        Customer customer = TestUtils.customer(false);
        CustomerEmailUpdateDto customerEmailUpdateDto = CustomerEmailUpdateDto.builder()
                .oldEmail("wrong@example.com")
                .newEmail("newemail@example.com")
                .build();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            customerServiceImpl.updateCustomerEmail(customer.getId(), customerEmailUpdateDto);
        });
    }

    @Test
    void updateCustomerPhoneSucceeds() {
        Customer customer = TestUtils.customer(false);
        CustomerPhoneUpdateDto customerPhoneUpdateDto = CustomerPhoneUpdateDto.builder()
                .phone("9999999999")
                .build();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResponseDto result = customerServiceImpl.updateCustomerPhone(customer.getId(), customerPhoneUpdateDto);

        assertThat(result.getPhone()).isEqualTo("9999999999");
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
