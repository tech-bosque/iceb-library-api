package com.iceb.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceb.library.TestUtils;
import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.service.BorrowService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@WebMvcTest(BorrowController.class)
public class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BorrowService borrowService;

    @Test
    void createBorrowTest() throws Exception {
        BorrowResponseDto borrowResponseDto = TestUtils.borrowResponseDto();

        when(borrowService.createBorrow(any(BorrowRequestDto.class))).thenReturn(borrowResponseDto);

        ResultActions result = mockMvc.perform(post("/api/borrow")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.borrowRequestDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BorrowResponseDto response = mapper.readValue(resultString, BorrowResponseDto.class);

        Assertions.assertEquals(borrowResponseDto, response);
    }

    @Test
    void getBorrowByIdTest() throws Exception {

        BorrowResponseDto borrowResponseDto = TestUtils.borrowResponseDto();

        when(borrowService.getBorrowById(any(UUID.class))).thenReturn(borrowResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/borrow/{id}", UUID.randomUUID())
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BorrowResponseDto response = mapper.readValue(resultString, BorrowResponseDto.class);

        Assertions.assertEquals(borrowResponseDto, response);
    }

    @Test
    void searchBorrowsTest() throws Exception {

        List<BorrowResponseDto> borrowResponseDtos = List.of(TestUtils.borrowResponseDto());

        when(borrowService.searchBorrows(any(BorrowSearchDto.class))).thenReturn(borrowResponseDtos);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/search")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.borrowSearchDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        List<BorrowResponseDto> response = mapper.readValue(resultString, new TypeReference<>() {
        });
        Assertions.assertEquals(borrowResponseDtos, response);
    }

    @Test
    void updateBorrowItemTest() throws Exception {

        BorrowResponseDto borrowResponseDto = TestUtils.borrowResponseDto();

        when(borrowService.updateBorrowItem(any(UUID.class), any(BorrowItemUpdateDto.class))).thenReturn(borrowResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/borrow/update-borrow-item/{id}", UUID.randomUUID())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.borrowItemUpdateDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BorrowResponseDto response = mapper.readValue(resultString, BorrowResponseDto.class);

        Assertions.assertEquals(borrowResponseDto, response);
    }

    @Test
    void deleteBorrowTest() throws Exception {

        BorrowResponseDto borrowResponseDto = TestUtils.borrowResponseDto();

        when(borrowService.deleteBorrow(borrowResponseDto.getId())).thenReturn(borrowResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/borrow/{id}", borrowResponseDto.getId())
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BorrowResponseDto response = mapper.readValue(resultString, BorrowResponseDto.class);

        Assertions.assertEquals(borrowResponseDto, response);
    }

    @Test
    void borrowItemReturnTest() throws Exception {

        BorrowResponseDto borrowResponseDto = TestUtils.borrowResponseDto();

        when(borrowService.borrowItemReturn(any(BorrowItemReturnDto.class))).thenReturn(borrowResponseDto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/return")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestUtils.borrowItemReturnDto()))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        BorrowResponseDto response = mapper.readValue(resultString, BorrowResponseDto.class);

        Assertions.assertEquals(borrowResponseDto, response);
    }
}
