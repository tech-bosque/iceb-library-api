package com.iceb.library.controller;

import com.iceb.library.dto.genre.GenreRequestDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genre")
@Tag(name = "Genre Management", description = "APIs for managing Genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    @Operation(summary = "Create a new genre")
    public ResponseEntity<GenreResponseDto> createGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        GenreResponseDto createdGenre = genreService.createGenre(genreRequestDto);
        return ResponseEntity.ok(createdGenre);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a genre by ID")
    public ResponseEntity<GenreResponseDto> getGenreById(@PathVariable UUID id) {
        GenreResponseDto genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @GetMapping
    @Operation(summary = "Get all genres or search by name")
    public ResponseEntity<List<GenreResponseDto>> searchGenres(@RequestParam(required = false) String name, @RequestParam(defaultValue = "false") boolean archived) {
        List<GenreResponseDto> genres = genreService.searchGenres(name, archived);
        return ResponseEntity.ok(genres);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a genre")
    public ResponseEntity<GenreResponseDto> updateGenre(@PathVariable UUID id, @Valid @RequestBody GenreRequestDto genreRequestDto) {
        GenreResponseDto updatedGenre = genreService.updateGenre(id, genreRequestDto);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a genre")
    public ResponseEntity<GenreResponseDto> deleteGenre(@PathVariable UUID id) {
        GenreResponseDto archivedGenre = genreService.deleteGenre(id);
        return ResponseEntity.ok(archivedGenre);
    }
}
