package com.iceb.library.service.impl;

import com.iceb.library.dto.AuthorRequestDto;
import com.iceb.library.dto.AuthorResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.exception.AuthorNotFoundException;
import com.iceb.library.repository.AuthorRepository;
import com.iceb.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        logger.info("Creating author");
        logger.debug("Creating author with details: {}", authorRequestDto);

        Author author = Author.builder()
                .name(authorRequestDto.getName())
                .archived(false)
                .build();
        Author savedAuthor = authorRepository.save(author);

        logger.info("Created author successfully");
        logger.debug("Created author: {}", savedAuthor);

        return mapToResponseDto(savedAuthor);
    }

    @Override
    public AuthorResponseDto getAuthorById(UUID id) {
        logger.info("Fetching author by ID");
        logger.debug("Fetching author with ID: {}", id);

        Author author = findAuthorById(id);
        AuthorResponseDto responseDto = mapToResponseDto(author);

        logger.debug("Fetched author: {}", responseDto);
        logger.info("Fetched author by ID successfully");
        return responseDto;
    }

    @Override
    public List<AuthorResponseDto> searchAuthors(String name, boolean archived) {
        logger.info("Fetching authors by name containing");
        logger.debug("Fetching authors with name containing: {}", name);

        List<Author> authors;
        if (name == null || name.trim().isEmpty()) {
            authors = authorRepository.findAllAuthors(archived);
        } else {
            authors = authorRepository.findSimilarNames(name, archived);
        }

        List<AuthorResponseDto> responseDtos = authors.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());

        logger.debug("Fetched authors: {}", responseDtos);
        logger.info("Fetched authors by name containing successfully");
        return responseDtos;
    }

    @Override
    public AuthorResponseDto updateAuthor(UUID id, AuthorRequestDto authorRequestDto) {
        logger.info("Updating author");
        logger.debug("Updating author with ID: {} with details: {}", id, authorRequestDto);

        Author existingAuthor = findAuthorById(id);
        existingAuthor.setName(authorRequestDto.getName());
        Author updatedAuthor = authorRepository.save(existingAuthor);

        logger.info("Updated author successfully");
        logger.debug("Updated author: {}", updatedAuthor);

        return mapToResponseDto(updatedAuthor);
    }

    @Override
    public AuthorResponseDto deleteAuthor(UUID id) {
        logger.info("Archiving author");
        logger.debug("Archiving author with ID: {}", id);

        Author author = findAuthorById(id);
        author.setArchived(true);
        Author archivedAuthor = authorRepository.save(author);

        logger.info("Archived author successfully");
        logger.debug("Archived author with ID: {}", id);

        return mapToResponseDto(archivedAuthor);
    }

    private Author findAuthorById(UUID id) {
        logger.info("Finding author by ID");
        logger.debug("Finding author with ID: {}", id);

        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The author with the provided ID does not exist"));
    }

    private AuthorResponseDto mapToResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .archived(author.getArchived())
                .build();
    }
}
