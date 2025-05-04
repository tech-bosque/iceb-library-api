package com.iceb.library.repository;

import com.iceb.library.entity.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreRepositoryTest extends TestRepositoryHelper{

    @Test
    void findGenreByIdTest() {
        Genre savedGenre = genreRepository.findById(genreTest.getId()).orElse(null);
        Assertions.assertNotNull(savedGenre);
        assertThat(savedGenre).usingRecursiveComparison().isEqualTo(genreTest);
    }

    @Test
    void findAllGenresTest() {
        Genre secondGenre = Genre.builder()
                .id(null)
                .name("Second Genre")
                .archived(false)
                .build();

        genreRepository.save(secondGenre);

        List<Genre> savedGenres = genreRepository.findAll();
        Assertions.assertNotNull(savedGenres);
        Assertions.assertFalse(savedGenres.isEmpty());
        Assertions.assertEquals(2, savedGenres.size());
    }

    @Test
    void findSimilarNamesTest() {
        List<Genre> savedGenres = genreRepository.findSimilarNames(genreTest.getName(), Arrays.asList(false));
        Assertions.assertNotNull(savedGenres);
        Assertions.assertFalse(savedGenres.isEmpty());
        Assertions.assertEquals(1, savedGenres.size());
    }
}
