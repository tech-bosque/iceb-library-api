package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.genre.GenreRequestDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.entity.Genre;
import com.iceb.library.exception.GenreNotFoundException;
import com.iceb.library.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceImplTest {

    @InjectMocks
    private GenreServiceImpl genreServiceImpl;

    @Mock
    private GenreRepository genreRepository;

    @Test
    void createGenreTest() {
        when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(TestUtils.genre(false));

        GenreRequestDto genreRequestDto = TestUtils.genreRequestDto();

        GenreResponseDto genreResponseDto = genreServiceImpl.createGenre(genreRequestDto);

        Assertions.assertEquals(genreResponseDto.getName(), genreRequestDto.getName());
        Assertions.assertFalse(genreResponseDto.getArchived());
    }

    @Test
    void getGenreByIdTest() {

        Genre genre = TestUtils.genre(false);

        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));

        GenreResponseDto genreResponseDto = genreServiceImpl.getGenreById(genre.getId());

        Assertions.assertEquals(genreResponseDto.getName(), genre.getName());
        Assertions.assertFalse(genreResponseDto.getArchived());
    }

    @Test
    void getGenreByIdNotFoundTest() {

        UUID genreId = UUID.randomUUID();

        when(genreRepository.findById(genreId)).thenReturn(Optional.empty());

        Assertions.assertThrows(GenreNotFoundException.class, () -> {
            genreServiceImpl.getGenreById(genreId);
        });
    }

    @Test
    void searchGenresTest() {
        List<Genre> genres = List.of(TestUtils.genre(false), TestUtils.genre(true));

        when(genreRepository.findAllGenres(false)).thenReturn(List.of(genres.get(0)));

        List<GenreResponseDto> genreResponseDtos = genreServiceImpl.searchGenres(null, false);

        Assertions.assertEquals(1, genreResponseDtos.size());
        Assertions.assertFalse(genres.get(0).getArchived());

        when(genreRepository.findSimilarNames("Test Genre", true)).thenReturn(List.of(genres.get(1)));

        List<GenreResponseDto> genreResponseDtos2 = genreServiceImpl.searchGenres("Test Genre", true);

        Assertions.assertEquals(1, genreResponseDtos2.size());
        Assertions.assertTrue(genres.get(1).getArchived());
    }

    @Test
    void updateGenreTest() {
        Genre genre = TestUtils.genre(false);
        GenreRequestDto genreRequestDto = TestUtils.genreRequestDto();
        genreRequestDto.setName("Updated Genre");

        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
        when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(genre);

        GenreResponseDto genreResponseDto = genreServiceImpl.updateGenre(genre.getId(), genreRequestDto);

        Assertions.assertEquals(genreResponseDto.getName(), genreRequestDto.getName());
        Assertions.assertFalse(genreResponseDto.getArchived());
    }

    @Test
    void deleteGenreTest() {
        Genre genre = TestUtils.genre(false);

        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
        when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(genre);

        GenreResponseDto genreResponseDto = genreServiceImpl.deleteGenre(genre.getId());

        Assertions.assertEquals(genre.getName(), genreResponseDto.getName());
        Assertions.assertTrue(genreResponseDto.getArchived());
    }


}
