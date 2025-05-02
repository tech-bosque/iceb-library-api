package com.iceb.library.service;

import com.iceb.library.dto.AuthorRequestDto;
import com.iceb.library.dto.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);
    AuthorResponseDto getAuthorById(UUID id);
    List<AuthorResponseDto> searchAuthors(String name, List<Boolean> archived);
    AuthorResponseDto updateAuthor(UUID id, AuthorRequestDto authorRequestDto);
    AuthorResponseDto deleteAuthor(UUID id);
}
