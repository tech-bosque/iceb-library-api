package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.author.AuthorRequestDto;
import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.service.AuthorService;
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

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService authorService;


    @Test
    void createAuthorTest() throws Exception {

        AuthorResponseDto authorResponseDto = TestUtils.authorResponseDto(false);

        when(authorService.createAuthor(any(AuthorRequestDto.class))).thenReturn(authorResponseDto);

        ResultActions result = mockMvc.perform(post("/api/author")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.authorRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        AuthorResponseDto response = mapper.readValue(resultString, AuthorResponseDto.class);

        Assertions.assertEquals(authorResponseDto, response);
    }

    @Test
    void getAuthorByIdTest() throws Exception {

        AuthorResponseDto authorResponseDto = TestUtils.authorResponseDto(false);

        when(authorService.getAuthorById(any(UUID.class))).thenReturn(authorResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/author/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        AuthorResponseDto response = mapper.readValue(resultString, AuthorResponseDto.class);

        Assertions.assertEquals(authorResponseDto, response);
    }

    @Test
    void searchAuthorsTest() throws Exception {

        List<AuthorResponseDto> authorResponseDtos = List.of(TestUtils.authorResponseDto(false));

        when(authorService.searchAuthors(any(String.class), any(Boolean.class))).thenReturn(authorResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/author")
                        .param("name", "Test Author")
                        .param("archived", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<AuthorResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(authorResponseDtos, response);
    }

    @Test
    void updateAuthorTest() throws Exception {

        AuthorResponseDto authorResponseDto = TestUtils.authorResponseDto(false);

        when(authorService.updateAuthor(authorResponseDto.getId(), TestUtils.authorRequestDto())).thenReturn(authorResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/author/{id}", authorResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.authorRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        AuthorResponseDto response = mapper.readValue(resultString, AuthorResponseDto.class);

        Assertions.assertEquals(authorResponseDto, response);
    }

    @Test
    void deleteAuthorTest() throws Exception {

        AuthorResponseDto authorResponseDto = TestUtils.authorResponseDto(true);

        when(authorService.deleteAuthor(authorResponseDto.getId())).thenReturn(authorResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/author/{id}", authorResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        AuthorResponseDto response = mapper.readValue(resultString, AuthorResponseDto.class);

        Assertions.assertEquals(authorResponseDto, response);
    }

}
