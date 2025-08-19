package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerSearchDto;
import com.iceb.library.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomerTest() throws Exception {
        CustomerResponseDto customerResponseDto = TestUtils.customerResponseDto(false);

        when(customerService.createCustomer(any(CustomerRequestDto.class))).thenReturn(customerResponseDto);

        ResultActions result = mockMvc.perform(post("/api/customer")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.customerRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        CustomerResponseDto response = mapper.readValue(resultString, CustomerResponseDto.class);

        Assertions.assertEquals(customerResponseDto, response);
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        CustomerResponseDto customerResponseDto = TestUtils.customerResponseDto(false);

        when(customerService.getCustomerById(any(UUID.class))).thenReturn(customerResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        CustomerResponseDto response = mapper.readValue(resultString, CustomerResponseDto.class);

        Assertions.assertEquals(customerResponseDto, response);
    }

    @Test
    void searchCustomersTest() throws Exception {
        List<CustomerResponseDto> customerResponseDtos = List.of(TestUtils.customerResponseDto(false));

        when(customerService.searchCustomers(any(CustomerSearchDto.class))).thenReturn(customerResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/search")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.customerSearchDto()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<CustomerResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(customerResponseDtos, response);
    }

    @Test
    void updateCustomerTest() throws Exception {
        CustomerResponseDto customerResponseDto = TestUtils.customerResponseDto(false);

        when(customerService.updateCustomer(any(UUID.class), any(CustomerRequestDto.class))).thenReturn(customerResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/{id}", customerResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.customerRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        CustomerResponseDto response = mapper.readValue(resultString, CustomerResponseDto.class);

        Assertions.assertEquals(customerResponseDto, response);
    }

    @Test
    void deleteCustomerTest() throws Exception {
        CustomerResponseDto customerResponseDto = TestUtils.customerResponseDto(true);

        when(customerService.deleteCustomer(customerResponseDto.getId())).thenReturn(customerResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/customer/{id}", customerResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        CustomerResponseDto response = mapper.readValue(resultString, CustomerResponseDto.class);

        Assertions.assertEquals(customerResponseDto, response);
    }
}
