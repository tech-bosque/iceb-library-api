package com.iceb.library.repository;

import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.entity.Customer;
import com.iceb.library.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryTest extends TestRepositoryHelper {
    @Test
    void findUserByIdTest() {
        Customer savedCustomer = customerRepository.findById(customerTest.getId()).orElse(null);
        Assertions.assertNotNull(savedCustomer);
        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customerTest);
    }

    @Test
    void searchCustomersWithoutParamsTest() {
        Customer secondCustomer = Customer.builder()
                .id(null)
                .name("Second Customer")
                .email("second@test.com")
                .phone("1234567890")
                .password("test")
                .roles(Arrays.asList(Role.CUSTOMER))
                .archived(false)
                .build();

        customerRepository.save(secondCustomer);

        CustomerSearchDto customerSearchDto = CustomerSearchDto.builder().build();

        List<Customer> savedCustomers = customerRepository.searchCustomers(customerSearchDto);
        Assertions.assertNotNull(savedCustomers);
        Assertions.assertFalse(savedCustomers.isEmpty());
        Assertions.assertEquals(2, savedCustomers.size());
    }

    @Test
    void searchCustomersByNameTest() {
        CustomerSearchDto customerSearchDto = CustomerSearchDto.builder()
                .name(customerTest.getName())
                .build();

        List<Customer> savedCustomers = customerRepository.searchCustomers(customerSearchDto);
        Assertions.assertNotNull(savedCustomers);
        Assertions.assertFalse(savedCustomers.isEmpty());

        Customer savedCustomer = savedCustomers.getFirst();
        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customerTest);
    }

    @Test
    void searchCostumersByEmailTest() {
        CustomerSearchDto customerSearchDto = CustomerSearchDto.builder()
                .email(customerTest.getEmail())
                .build();

        List<Customer> savedCustomers = customerRepository.searchCustomers(customerSearchDto);
        Assertions.assertNotNull(savedCustomers);
        Assertions.assertFalse(savedCustomers.isEmpty());

        Customer savedCustomer = savedCustomers.getFirst();
        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customerTest);
    }

    @Test
    void searchCostumersByPhoneTest() {
        CustomerSearchDto customerSearchDto = CustomerSearchDto.builder()
                .phone(customerTest.getPhone())
                .build();

        List<Customer> savedCustomers = customerRepository.searchCustomers(customerSearchDto);
        Assertions.assertNotNull(savedCustomers);
        Assertions.assertFalse(savedCustomers.isEmpty());

        Customer savedCustomer = savedCustomers.getFirst();
        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customerTest);
    }

    @Test
    void searchCostumersByRoleTest() {
        CustomerSearchDto customerSearchDto = CustomerSearchDto.builder()
                .role(customerTest.getRoles().toString())
                .build();

        List<Customer> savedCustomers = customerRepository.searchCustomers(customerSearchDto);
        Assertions.assertNotNull(savedCustomers);
        Assertions.assertFalse(savedCustomers.isEmpty());

        Customer savedCustomer = savedCustomers.getFirst();
        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customerTest);
    }
}
