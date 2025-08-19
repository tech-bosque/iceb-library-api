package com.iceb.library.service.impl;

import com.iceb.library.dto.genre.GenreRequestDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.entity.Genre;
import com.iceb.library.exception.GenreNotFoundException;
import com.iceb.library.repository.GenreRepository;
import com.iceb.library.service.GenreService;
import com.iceb.library.utils.TranslatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreResponseDto createGenre(GenreRequestDto genreRequestDto) {
        logger.info("Creating genre");
        logger.debug("Creating genre with details: {}", genreRequestDto);

        Genre genre = Genre.builder()
                .name(genreRequestDto.getName())
                .archived(false)
                .build();
        Genre savedGenre = genreRepository.save(genre);

        logger.info("Created genre successfully");
        logger.debug("Created genre: {}", savedGenre);

        return TranslatorUtils.genreToGenreResponseDto(savedGenre);
    }

    @Override
    public GenreResponseDto getGenreById(UUID id) {
        logger.info("Fetching genre by ID");
        logger.debug("Fetching genre with ID: {}", id);

        Genre genre = findGenreById(id);
        GenreResponseDto responseDto = TranslatorUtils.genreToGenreResponseDto(genre);

        logger.debug("Fetched genre: {}", responseDto);
        logger.info("Fetched genre by ID successfully");
        return responseDto;
    }

    @Override
    public List<GenreResponseDto> searchGenres(String name, boolean archived) {
        logger.info("Fetching genres by name containing");
        logger.debug("Fetching genres with name containing: {}", name);

        List<Genre> genres;
        if (name == null || name.trim().isEmpty()) {
            genres = genreRepository.findAllGenres(archived);
        } else {
            genres = genreRepository.findSimilarNames(name, archived);
        }

        List<GenreResponseDto> responseDtos = genres.stream()
                .map(TranslatorUtils::genreToGenreResponseDto)
                .collect(Collectors.toList());

        logger.debug("Fetched genres: {}", responseDtos);
        logger.info("Fetched genres by name containing successfully");
        return responseDtos;
    }

    @Override
    public GenreResponseDto updateGenre(UUID id, GenreRequestDto genreRequestDto) {
        logger.info("Updating genre");
        logger.debug("Updating genre with ID: {} with details: {}", id, genreRequestDto);

        Genre existingGenre = findGenreById(id);
        existingGenre.setName(genreRequestDto.getName());
        Genre updatedGenre = genreRepository.save(existingGenre);

        logger.info("Updated genre successfully");
        logger.debug("Updated genre: {}", updatedGenre);

        return TranslatorUtils.genreToGenreResponseDto(updatedGenre);
    }

    @Override
    public GenreResponseDto deleteGenre(UUID id) {
        logger.info("Archiving genre");
        logger.debug("Archiving genre with ID: {}", id);

        Genre genre = findGenreById(id);
        genre.setArchived(true);
        Genre archivedGenre = genreRepository.save(genre);

        logger.info("Archived genre successfully");
        logger.debug("Archived genre with ID: {}", id);

        return TranslatorUtils.genreToGenreResponseDto(archivedGenre);
    }

    private Genre findGenreById(UUID id) {
        logger.info("Finding genre by ID");
        logger.debug("Finding genre with ID: {}", id);

        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("The genre with the provided ID does not exist"));
    }

}
