package com.iceb.library.validation;

import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.borrow.BorrowItemRequestDto;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.entity.Customer;
import com.iceb.library.exception.BookNotAvailableException;
import com.iceb.library.service.BookService;
import com.iceb.library.service.CustomerService;
import com.iceb.library.utils.TranslatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BorrowValidator {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    public Customer validateCustomerId(UUID customerId) {
        if (customerId == null) {
            throw new NullPointerException("Customer ID cannot be null");
        }
        return TranslatorUtils.customerResponseDtoToCustomer(customerService.getCustomerById(customerId));
    }

    public BorrowItem validateBorrowItem(BorrowItemRequestDto borrowItem) {

        if(borrowItem.getBookId() == null) {
            throw new NullPointerException("Borrow item cannot be null");
        }

        BookResponseDto book = bookService.getBookById(borrowItem.getBookId());

        if (book.getAvailable() != null && !book.getAvailable()) {
            throw new BookNotAvailableException("The book with id: " + book.getId() + " and name " +
                    book.getName() + " is not available");
        }
        return TranslatorUtils.borrowItemRequestDtoToBorrowItem(borrowItem, book);
    }


}
