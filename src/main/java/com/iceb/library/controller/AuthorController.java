package com.iceb.library.controller;

import com.iceb.library.dto.AuthorRequestDto;
import com.iceb.library.dto.AuthorResponseDto;
import com.iceb.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/author")
@Tag(name = "Author Management", description = "APIs for managing Author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    @Operation(summary = "Create a new author")
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto createdAuthor = authorService.createAuthor(authorRequestDto);
        return ResponseEntity.ok(createdAuthor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an author by ID")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable UUID id) {
        AuthorResponseDto author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    @Operation(summary = "Get all authors or search by name")
    public ResponseEntity<List<AuthorResponseDto>> searchAuthors(
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "false") String archivedStr
    ) {

        List<Boolean> archived = Arrays.stream(archivedStr.split(","))
                                       .map(Boolean::parseBoolean)
                                       .collect(Collectors.toList());

        List<AuthorResponseDto> authors = authorService.searchAuthors(name, archived);
        return ResponseEntity.ok(authors);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an author")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable UUID id, @Valid @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto updatedAuthor = authorService.updateAuthor(id, authorRequestDto);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an author")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable UUID id) {
        AuthorResponseDto archivedAuthor = authorService.deleteAuthor(id);
        return ResponseEntity.ok(archivedAuthor);
    }
}
