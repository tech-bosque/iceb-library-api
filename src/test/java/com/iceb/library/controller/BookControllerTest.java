package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.book.BookRequestDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.book.BookSearchDto;
import com.iceb.library.service.BookService;
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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;


    @Test
    void createBookTest() throws Exception {

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(false);

        when(bookService.createBook(any(BookRequestDto.class))).thenReturn(bookResponseDto);

        ResultActions result = mockMvc.perform(post("/api/book")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.bookRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BookResponseDto response = mapper.readValue(resultString, BookResponseDto.class);

        Assertions.assertEquals(bookResponseDto, response);
    }

    @Test
    void getBookByIdTest() throws Exception {

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(false);

        when(bookService.getBookById(any(UUID.class))).thenReturn(bookResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/book/{id}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BookResponseDto response = mapper.readValue(resultString, BookResponseDto.class);

        Assertions.assertEquals(bookResponseDto, response);
    }

    @Test
    void searchBooksTest() throws Exception {

        List<BookResponseDto> bookResponseDtos = List.of(TestUtils.bookResponseDto(false));

        when(bookService.searchBooks(any(BookSearchDto.class))).thenReturn(bookResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/search")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.bookSearchDto()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<BookResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });

        Assertions.assertEquals(bookResponseDtos, response);
    }

    @Test
    void updateBookTest() throws Exception {

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(false);

        when(bookService.updateBook(any(UUID.class), any(BookRequestDto.class))).thenReturn(bookResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/book/{id}", bookResponseDto.getId())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.bookRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BookResponseDto response = mapper.readValue(resultString, BookResponseDto.class);

        Assertions.assertEquals(bookResponseDto, response);
    }

    @Test
    void deleteBookTest() throws Exception {

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(true);

        when(bookService.deleteBook(bookResponseDto.getId())).thenReturn(bookResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/{id}", bookResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BookResponseDto response = mapper.readValue(resultString, BookResponseDto.class);

        Assertions.assertEquals(bookResponseDto, response);
    }

}
