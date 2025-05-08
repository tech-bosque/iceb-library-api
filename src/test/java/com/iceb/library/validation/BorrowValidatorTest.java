package com.iceb.library.validation;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.borrow.BorrowItemRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.BookNotAvailableException;
import com.iceb.library.service.BookService;
import com.iceb.library.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowValidatorTest {

    @InjectMocks
    private BorrowValidator borrowValidator;

    @Mock
    private CustomerService customerService;

    @Mock
    private BookService bookService;

    @Test
    void validateCustomerIdTest(){

        CustomerResponseDto customerResponseDto = TestUtils.customerResponseDto(false);
        Customer customer = TestUtils.customer(false);

        when(customerService.getCustomerById(customerResponseDto.getId())).thenReturn(customerResponseDto);

        Customer result = borrowValidator.validateCustomerId(customerResponseDto.getId());

        assertThat(customer).usingRecursiveComparison().ignoringFields("password").isEqualTo(result);
    }

    @Test
    void validateCustomerIdNullPointerException(){

        Assertions.assertThrows(NullPointerException.class, () -> {
            borrowValidator.validateCustomerId(null);
        });

    }

    @Test
    void validateBorrowItemTest(){

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(false);
        bookResponseDto.setAvailable(true);

        BorrowItemRequestDto borrowItemRequestDto = TestUtils.borrowItemRequestDto();

        when(bookService.getBookById(borrowItemRequestDto.getBookId())).thenReturn(bookResponseDto);

        BorrowItem result = borrowValidator.validateBorrowItem(borrowItemRequestDto);

        assertThat(bookResponseDto.getId()).usingRecursiveComparison().isEqualTo(result.getBookId());

    }

    @Test
    void validateBorrowItemWhenBookNotAvailableTest(){

        BookResponseDto bookResponseDto = TestUtils.bookResponseDto(false);
        bookResponseDto.setAvailable(false);

        BorrowItemRequestDto borrowItemRequestDto = TestUtils.borrowItemRequestDto();

        when(bookService.getBookById(borrowItemRequestDto.getBookId())).thenReturn(bookResponseDto);

        Assertions.assertThrows(BookNotAvailableException.class, () -> {
            borrowValidator.validateBorrowItem(borrowItemRequestDto);
        });

    }

    @Test
    void validateBorrowItemNullPointerException(){

        Assertions.assertThrows(NullPointerException.class, () -> {
            borrowValidator.validateBorrowItem(null);
        });

    }
}
