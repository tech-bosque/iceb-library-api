package com.iceb.library.config;

import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.enums.Role;
import com.iceb.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        CustomerRequestDto customerRequestDto =  CustomerRequestDto.builder()
                                                    .name("Admin User")
                                                    .email("admin@test.com")
                                                    .password("admin")
                                                    .phone("1234567890")
                                                    .role(Role.ROLE_ADMIN)
                                                    .build();


        customerService.createCustomer(customerRequestDto);

    }
}
