package com.iceb.library.controller;

import com.iceb.library.dto.BookRequestDto;
import com.iceb.library.dto.BookResponseDto;
import com.iceb.library.dto.BookSearchDto;
import com.iceb.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book Management", description = "APIs for managing Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto createdBook = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable UUID id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/search")
    @Operation(summary = "Get all books or search with filters")
    public ResponseEntity<List<BookResponseDto>> searchBooks(@RequestBody @Valid BookSearchDto bookSearchDto) {
        List<BookResponseDto> books = bookService.searchBooks(bookSearchDto);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable UUID id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto updatedBook = bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable UUID id) {
        BookResponseDto archivedBook = bookService.deleteBook(id);
        return ResponseEntity.ok(archivedBook);
    }
}
