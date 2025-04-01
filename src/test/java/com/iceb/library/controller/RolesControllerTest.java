package com.iceb.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.RolesResponseDto;
import com.iceb.library.service.RolesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RolesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RolesService rolesService;

    @Test
    void getRolesTest() throws Exception {

        RolesResponseDto rolesResponseDto = TestUtils.rolesResponseDto();

        when(rolesService.getRoles()).thenReturn(rolesResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/roles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        RolesResponseDto response = mapper.readValue(resultString, RolesResponseDto.class);

        Assertions.assertEquals(rolesResponseDto, response);
    }

}
