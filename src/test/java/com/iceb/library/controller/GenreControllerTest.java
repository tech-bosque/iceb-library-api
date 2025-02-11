package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.GenreRequestDto;
import com.iceb.library.dto.GenreResponseDto;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.service.GenreService;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService genreService;


    @Test
    void createGenreTest() throws Exception {

        GenreResponseDto genreResponseDto = TestUtils.genreResponseDto(false);

        when(genreService.createGenre(any(GenreRequestDto.class))).thenReturn(genreResponseDto);

        ResultActions result = mockMvc.perform(post("/api/genre")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.genreRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        GenreResponseDto response = mapper.readValue(resultString, GenreResponseDto.class);

        Assertions.assertEquals(genreResponseDto, response);
    }

    @Test
    void getGenreByIdTest() throws Exception {

        GenreResponseDto genreResponseDto = TestUtils.genreResponseDto(false);

        when(genreService.getGenreById(any(UUID.class))).thenReturn(genreResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/genre/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        GenreResponseDto response = mapper.readValue(resultString, GenreResponseDto.class);

        Assertions.assertEquals(genreResponseDto, response);
    }

    @Test
    void searchGenresTest() throws Exception {

        List<GenreResponseDto> genreResponseDtos = List.of(TestUtils.genreResponseDto(false));

        when(genreService.searchGenres(any(String.class), any(Boolean.class))).thenReturn(genreResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/genre")
                        .param("name", "Test Genre")
                        .param("archived", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<GenreResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(genreResponseDtos, response);
    }

    @Test
    void updateGenreTest() throws Exception {

        GenreResponseDto genreResponseDto = TestUtils.genreResponseDto(false);

        when(genreService.updateGenre(genreResponseDto.getId(), TestUtils.genreRequestDto())).thenReturn(genreResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/genre/{id}", genreResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.genreRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        GenreResponseDto response = mapper.readValue(resultString, GenreResponseDto.class);

        Assertions.assertEquals(genreResponseDto, response);
    }

    @Test
    void deleteGenreTest() throws Exception {

        GenreResponseDto genreResponseDto = TestUtils.genreResponseDto(true);

        when(genreService.deleteGenre(genreResponseDto.getId())).thenReturn(genreResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/genre/{id}", genreResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        GenreResponseDto response = mapper.readValue(resultString, GenreResponseDto.class);

        Assertions.assertEquals(genreResponseDto, response);
    }

}
