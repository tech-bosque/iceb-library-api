package com.iceb.library.service;

import com.iceb.library.dto.book.BookRequestDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.book.BookSearchDto;

import java.util.List;
import java.util.UUID;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);
    BookResponseDto getBookById(UUID id);
    List<BookResponseDto> searchBooks(BookSearchDto bookSearchDto);
    BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto);
    BookResponseDto deleteBook(UUID id);
    Boolean isBookAvailable(UUID id);
    void updateBookAvailability(UUID id, Boolean available);
}
