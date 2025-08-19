package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.publisher.PublisherRequestDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.security.JwtDecoder;
import com.iceb.library.security.JwtToPrincipalConverter;
import com.iceb.library.service.PublisherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
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

@WithMockUser(roles = "ADMIN")
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {

    @MockBean
    private JwtDecoder jwtDecoder;

    @MockBean
    private JwtToPrincipalConverter jwtToPrincipalConverter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PublisherService publisherService;


    @Test
    void createPublisherTest() throws Exception {

        PublisherResponseDto publisherResponseDto = TestUtils.publisherResponseDto(false);

        when(publisherService.createPublisher(any(PublisherRequestDto.class))).thenReturn(publisherResponseDto);

        ResultActions result = mockMvc.perform(post("/api/publisher")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.publisherRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        PublisherResponseDto response = mapper.readValue(resultString, PublisherResponseDto.class);

        Assertions.assertEquals(publisherResponseDto, response);
    }

    @Test
    void getPublisherByIdTest() throws Exception {

        PublisherResponseDto publisherResponseDto = TestUtils.publisherResponseDto(false);

        when(publisherService.getPublisherById(any(UUID.class))).thenReturn(publisherResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/publisher/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        PublisherResponseDto response = mapper.readValue(resultString, PublisherResponseDto.class);

        Assertions.assertEquals(publisherResponseDto, response);
    }

    @Test
    void searchPublisherTest() throws Exception {

        List<PublisherResponseDto> publisherResponseDtos = List.of(TestUtils.publisherResponseDto(false));

        when(publisherService.searchPublishers(any(String.class), any(Boolean.class))).thenReturn(publisherResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/publisher")
                        .param("name", "Test Publisher")
                        .param("archived", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<PublisherResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(publisherResponseDtos, response);
    }

    @Test
    void updatePublisherTest() throws Exception {

        PublisherResponseDto publisherResponseDto = TestUtils.publisherResponseDto(false);

        when(publisherService.updatePublisher(publisherResponseDto.getId(), TestUtils.publisherRequestDto())).thenReturn(publisherResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/publisher/{id}", publisherResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.publisherRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        PublisherResponseDto response = mapper.readValue(resultString, PublisherResponseDto.class);

        Assertions.assertEquals(publisherResponseDto, response);
    }

    @Test
    void deletePublisherTest() throws Exception {

        PublisherResponseDto publisherResponseDto = TestUtils.publisherResponseDto(true);

        when(publisherService.deletePublisher(publisherResponseDto.getId())).thenReturn(publisherResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/publisher/{id}", publisherResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        PublisherResponseDto response = mapper.readValue(resultString, PublisherResponseDto.class);

        Assertions.assertEquals(publisherResponseDto, response);
    }

}
