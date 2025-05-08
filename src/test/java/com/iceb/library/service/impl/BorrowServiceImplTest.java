package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.entity.Borrow;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.BorrowNotFoundException;
import com.iceb.library.exception.BorrowItemNotFoundException;
import com.iceb.library.repository.BorrowRepository;
import com.iceb.library.service.BookService;
import com.iceb.library.validation.BorrowValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceImplTest {

    @InjectMocks
    private BorrowServiceImpl borrowServiceImpl;

    @Mock
    private BorrowValidator borrowValidator;

    @Mock
    private BorrowRepository borrowRepository;

    @Mock
    private BookService bookService;

    @Test
    void createBorrowTest() {

        BorrowRequestDto borrowRequestDto = TestUtils.borrowRequestDto();
        Borrow borrow = TestUtils.borrow();
        Customer customer = TestUtils.customer(false);
        List<BorrowItem> borrowItems = List.of(TestUtils.borrowItem());

        when(borrowValidator.validateCustomerId(borrowRequestDto.getCustomerId())).thenReturn(customer);
        when(borrowValidator.validateBorrowItem(borrowRequestDto.getBorrowItems().getFirst())).thenReturn(borrowItems.getFirst());

        when(borrowRepository.save(Mockito.any(Borrow.class))).thenReturn(borrow);

        BorrowResponseDto borrowResponseDto = borrowServiceImpl.createBorrow(borrowRequestDto);

        assertThat(borrowResponseDto).usingRecursiveComparison().ignoringFields("id", "archived", "available", "borrowItems").isEqualTo(borrowRequestDto);
    }

    @Test
    void getBorrowByIdTest() {

        Borrow borrow = TestUtils.borrow();

        when(borrowRepository.findById(borrow.getId())).thenReturn(java.util.Optional.of(borrow));

        BorrowResponseDto borrowResponseDto = borrowServiceImpl.getBorrowById(borrow.getId());

        assertThat(borrow).usingRecursiveComparison().ignoringFields("customer", "borrowItems").isEqualTo(borrowResponseDto);
    }

    @Test
    void getBorrowByIdNotFoundTest() {

        UUID borrowId = UUID.randomUUID();

        when(borrowRepository.findById(borrowId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BorrowNotFoundException.class, () -> {
            borrowServiceImpl.getBorrowById(borrowId);
        });

    }

    @Test
    void searchBorrowsTest() {
        List<Borrow> borrows = List.of(TestUtils.borrow(), TestUtils.borrow());

        when(borrowRepository.searchBorrows(TestUtils.borrowSearchDto())).thenReturn(borrows);

        List<BorrowResponseDto> borrowResponseDtos = borrowServiceImpl.searchBorrows(TestUtils.borrowSearchDto());

        Assertions.assertEquals(2, borrowResponseDtos.size());
        assertThat(borrows).usingRecursiveComparison().ignoringFields("customer", "borrowItems").isEqualTo(borrowResponseDtos);
    }

    @Test
    void updateBorrowItemTest() {
        Borrow borrow = TestUtils.borrow();

        BorrowItemUpdateDto borrowItemUpdateDto = TestUtils.borrowItemUpdateDto();
        borrowItemUpdateDto.setBookId(UUID.randomUUID());
        borrowItemUpdateDto.setActualReturnDate(LocalDate.now().plusDays(1));

        when(borrowRepository.findById(borrow.getId())).thenReturn(Optional.of(borrow));
        when(borrowRepository.save(Mockito.any(Borrow.class))).thenReturn(borrow);

        BorrowResponseDto borrowResponseDto = borrowServiceImpl.updateBorrowItem(borrow.getId(), borrowItemUpdateDto);

        Assertions.assertEquals(borrowItemUpdateDto.getBookId(), borrowResponseDto.getBorrowItems().getFirst().getBookId());
        Assertions.assertEquals(borrowItemUpdateDto.getActualReturnDate(), borrowResponseDto.getBorrowItems().getFirst().getActualReturnDate());
    }

    @Test
    void updateBorrowItemNotFoundTest() {
        Borrow borrow = TestUtils.borrow();

        BorrowItemUpdateDto borrowItemUpdateDto = TestUtils.borrowItemUpdateDto();
        borrowItemUpdateDto.setBorrowItemId(UUID.randomUUID());

        when(borrowRepository.findById(borrow.getId())).thenReturn(Optional.of(borrow));

        Assertions.assertThrows(BorrowItemNotFoundException.class, () -> {
            borrowServiceImpl.updateBorrowItem(borrow.getId(), borrowItemUpdateDto);
        });
    }

    @Test
    void borrowItemReturnTest() {
        Borrow borrow = TestUtils.borrow();
        borrow.getBorrowItems().getFirst().setActualReturnDate(null);

        BorrowItemReturnDto borrowItemReturnDto = TestUtils.borrowItemReturnDto();
        borrowItemReturnDto.setActualReturnDate(LocalDate.now().plusDays(1));

        when(borrowRepository.findById(borrowItemReturnDto.getBorrowId())).thenReturn(Optional.of(borrow));
        when(borrowRepository.save(Mockito.any(Borrow.class))).thenReturn(borrow);

        BorrowResponseDto borrowResponseDto = borrowServiceImpl.borrowItemReturn(borrowItemReturnDto);

        Assertions.assertEquals(borrow.getBorrowItems().getFirst().getBookId(), borrowResponseDto.getBorrowItems().getFirst().getBookId());
        Assertions.assertNotNull(borrowResponseDto.getBorrowItems().getFirst().getActualReturnDate());
    }

    @Test
    void borrowItemReturnNotFoundTest() {
        Borrow borrow = TestUtils.borrow();
        borrow.getBorrowItems().getFirst().setActualReturnDate(null);

        BorrowItemReturnDto borrowItemReturnDto = TestUtils.borrowItemReturnDto();
        borrowItemReturnDto.setBorrowItemId(UUID.randomUUID());

        when(borrowRepository.findById(borrowItemReturnDto.getBorrowId())).thenReturn(Optional.of(borrow));

        Assertions.assertThrows(BorrowItemNotFoundException.class, () -> {
            borrowServiceImpl.borrowItemReturn(borrowItemReturnDto);
        });
    }

}
