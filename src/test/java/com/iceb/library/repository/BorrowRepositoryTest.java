package com.iceb.library.repository;

import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.entity.Borrow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BorrowRepositoryTest extends TestRepositoryHelper{

    @Test
    void findBorrowByIdTest() {
        Borrow savedBorrow = borrowRepository.findById(borrowTest.getId()).orElse(null);
        Assertions.assertNotNull(savedBorrow);
        assertThat(savedBorrow).usingRecursiveComparison().isEqualTo(borrowTest);
    }

    @Test
    void searchBorrowWithoutParamsTest() {

        Borrow secondBorrow = Borrow.builder()
                .id(null)
                .borrowDate(LocalDate.now())
                .build();

        borrowRepository.save(secondBorrow);

        BorrowSearchDto borrowSearchDto = BorrowSearchDto.builder().build();

        List<Borrow> savedBorrows = borrowRepository.searchBorrows(borrowSearchDto);
        Assertions.assertNotNull(savedBorrows);
        Assertions.assertFalse(savedBorrows.isEmpty());
        Assertions.assertEquals(2, savedBorrows.size());
    }

    @Test
    void searchBorrowByCustomerIdTest() {
        BorrowSearchDto borrowSearchDto = BorrowSearchDto.builder()
                .customerId(borrowTest.getCustomer().getId())
                .build();

        List<Borrow> savedBorrows = borrowRepository.searchBorrows(borrowSearchDto);
        Assertions.assertNotNull(savedBorrows);
        Assertions.assertFalse(savedBorrows.isEmpty());

        Borrow savedBorrow = savedBorrows.getFirst();
        assertThat(savedBorrow).usingRecursiveComparison().isEqualTo(borrowTest);
    }

    @Test
    void searchBorrowByCustomerNameTest() {
        BorrowSearchDto borrowSearchDto = BorrowSearchDto.builder()
                .customerName(borrowTest.getCustomer().getName())
                .build();

        List<Borrow> savedBorrows = borrowRepository.searchBorrows(borrowSearchDto);
        Assertions.assertNotNull(savedBorrows);
        Assertions.assertFalse(savedBorrows.isEmpty());

        Borrow savedBorrow = savedBorrows.getFirst();
        assertThat(savedBorrow).usingRecursiveComparison().isEqualTo(borrowTest);
    }

    @Test
    void searchBorrowByBorrowDateTest() {
        BorrowSearchDto borrowSearchDto = BorrowSearchDto.builder()
                .borrowDate(borrowTest.getBorrowDate().toString())
                .build();

        List<Borrow> savedBorrows = borrowRepository.searchBorrows(borrowSearchDto);
        Assertions.assertNotNull(savedBorrows);
        Assertions.assertFalse(savedBorrows.isEmpty());

        Borrow savedBorrow = savedBorrows.getFirst();
        assertThat(savedBorrow).usingRecursiveComparison().isEqualTo(borrowTest);
    }
}
