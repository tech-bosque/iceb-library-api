package com.iceb.library.service;

import com.iceb.library.dto.GenreRequestDto;
import com.iceb.library.dto.GenreResponseDto;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    GenreResponseDto createGenre(GenreRequestDto genreRequestDto);
    GenreResponseDto getGenreById(UUID id);
    List<GenreResponseDto> searchGenres(String name, List<Boolean> archived);
    GenreResponseDto updateGenre(UUID id, GenreRequestDto genreRequestDto);
    GenreResponseDto deleteGenre(UUID id);
}
