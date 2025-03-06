package com.iceb.library.service;

import com.iceb.library.dto.BookRequestDto;
import com.iceb.library.dto.BookResponseDto;
import com.iceb.library.dto.BookSearchDto;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;

import java.util.List;
import java.util.UUID;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);
    BookResponseDto getBookById(UUID id);
    List<BookResponseDto> searchBooks(BookSearchDto bookSearchDto);
    BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto);
    BookResponseDto deleteBook(UUID id);
}
