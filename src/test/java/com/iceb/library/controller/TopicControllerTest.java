package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;
import com.iceb.library.service.TopicService;
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TopicService topicService;


    @Test
    void createTopicTest() throws Exception {

        TopicResponseDto topicResponseDto = TestUtils.topicResponseDto(false);

        when(topicService.createTopic(any(TopicRequestDto.class))).thenReturn(topicResponseDto);

        ResultActions result = mockMvc.perform(post("/api/topic")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.topicRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        TopicResponseDto response = mapper.readValue(resultString, TopicResponseDto.class);

        Assertions.assertEquals(topicResponseDto, response);
    }

    @Test
    void getTopicByIdTest() throws Exception {

        TopicResponseDto topicResponseDto = TestUtils.topicResponseDto(false);

        when(topicService.getTopicById(any(UUID.class))).thenReturn(topicResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/topic/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        TopicResponseDto response = mapper.readValue(resultString, TopicResponseDto.class);

        Assertions.assertEquals(topicResponseDto, response);
    }

    @Test
    void searchTopicTest() throws Exception {

        List<TopicResponseDto> topicResponseDtos = List.of(TestUtils.topicResponseDto(false));

        when(topicService.searchTopics(any(String.class), Arrays.asList(any(Boolean.class)))).thenReturn(topicResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/topic")
                        .param("name", "Test Topic")
                        .param("archived", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<TopicResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(topicResponseDtos, response);
    }

    @Test
    void updateTopicTest() throws Exception {

        TopicResponseDto topicResponseDto = TestUtils.topicResponseDto(false);

        when(topicService.updateTopic(topicResponseDto.getId(), TestUtils.topicRequestDto())).thenReturn(topicResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/topic/{id}", topicResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.topicRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        TopicResponseDto response = mapper.readValue(resultString, TopicResponseDto.class);

        Assertions.assertEquals(topicResponseDto, response);
    }

    @Test
    void deleteTopicTest() throws Exception {

        TopicResponseDto topicResponseDto = TestUtils.topicResponseDto(true);

        when(topicService.deleteTopic(topicResponseDto.getId())).thenReturn(topicResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/topic/{id}", topicResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        TopicResponseDto response = mapper.readValue(resultString, TopicResponseDto.class);

        Assertions.assertEquals(topicResponseDto, response);
    }

}
