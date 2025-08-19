package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.book.BookRequestDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.entity.Book;
import com.iceb.library.exception.BookNotFoundException;
import com.iceb.library.repository.BookRepository;
import com.iceb.library.validation.BookValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookValidator bookValidator;

    @Test
    void createBookTest() {

        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(TestUtils.book(false));

        BookRequestDto bookRequestDto = TestUtils.bookRequestDto();

        BookResponseDto bookResponseDto = bookServiceImpl.createBook(bookRequestDto);

        assertThat(bookResponseDto).usingRecursiveComparison().ignoringFields("id", "archived", "available").isEqualTo(bookRequestDto);
    }

    @Test
    void getBookByIdTest() {

        Book book = TestUtils.book(false);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        BookResponseDto bookResponseDto = bookServiceImpl.getBookById(book.getId());

        assertThat(book).usingRecursiveComparison().ignoringFields("topics", "genres", "publisher", "authors").isEqualTo(bookResponseDto);
    }

    @Test
    void getBookByIdNotFoundTest() {

        UUID bookId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotFoundException.class, () -> {
            bookServiceImpl.getBookById(bookId);
        });

    }

    @Test
    void searchBooksTest() {
        List<Book> books = List.of(TestUtils.book(false), TestUtils.book(true));

        when(bookRepository.searchBooks(TestUtils.bookSearchDto())).thenReturn(books);

        List<BookResponseDto> bookResponseDtos = bookServiceImpl.searchBooks(TestUtils.bookSearchDto());

        Assertions.assertEquals(2, bookResponseDtos.size());
        assertThat(books).usingRecursiveComparison().ignoringFields("topics", "genres", "publisher", "authors").isEqualTo(bookResponseDtos);
    }

    @Test
    void updateBookTest() {
        Book book = TestUtils.book(false);
        BookRequestDto bookRequestDto = TestUtils.bookRequestDto();
        bookRequestDto.setName("Updated Book");

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        BookResponseDto bookResponseDto = bookServiceImpl.updateBook(book.getId(), bookRequestDto);

        Assertions.assertEquals(bookResponseDto.getName(), bookRequestDto.getName());
        Assertions.assertFalse(bookResponseDto.getArchived());
    }

    @Test
    void deleteBookTest() {
        Book book = TestUtils.book(false);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        BookResponseDto bookResponseDto = bookServiceImpl.deleteBook(book.getId());

        Assertions.assertEquals(book.getName(), bookResponseDto.getName());
        Assertions.assertTrue(bookResponseDto.getArchived());
    }

    @Test
    void isBookAvailableTest() {
        Book book = TestUtils.book(false);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Boolean isAvailable = bookServiceImpl.isBookAvailable(book.getId());

        Assertions.assertTrue(isAvailable);
    }

    @Test
    void updateBookAvailabilityTest() {
        Book book = TestUtils.book(false);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        bookServiceImpl.updateBookAvailability(book.getId(), false);

        Assertions.assertFalse(book.getAvailable());
    }
}
