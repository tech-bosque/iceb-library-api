package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.author.AuthorRequestDto;
import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.exception.AuthorNotFoundException;
import com.iceb.library.repository.AuthorRepository;
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
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    void createAuthorTest() {
        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(TestUtils.author(false));

        AuthorRequestDto authorRequestDto = TestUtils.authorRequestDto();

        AuthorResponseDto authorResponseDto = authorServiceImpl.createAuthor(authorRequestDto);

        Assertions.assertEquals(authorResponseDto.getName(), authorRequestDto.getName());
        Assertions.assertFalse(authorResponseDto.getArchived());
    }

    @Test
    void getAuthorByIdTest() {

        Author author = TestUtils.author(false);

        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));

        AuthorResponseDto authorResponseDto = authorServiceImpl.getAuthorById(author.getId());

        Assertions.assertEquals(authorResponseDto.getName(), author.getName());
        Assertions.assertFalse(authorResponseDto.getArchived());
    }

    @Test
    void getAuthorByIdNotFoundTest() {

        UUID authorId = UUID.randomUUID();

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        Assertions.assertThrows(AuthorNotFoundException.class, () -> {
            authorServiceImpl.getAuthorById(authorId);
        });
    }

    @Test
    void searchAuthorsTest() {
        List<Author> authors = List.of(TestUtils.author(false), TestUtils.author(true));

        when(authorRepository.findAllAuthors(false)).thenReturn(List.of(authors.get(0)));

        List<AuthorResponseDto> authorResponseDtos = authorServiceImpl.searchAuthors(null, false);

        Assertions.assertEquals(1, authorResponseDtos.size());
        Assertions.assertFalse(authors.get(0).getArchived());

        when(authorRepository.findSimilarNames("Test Author", true)).thenReturn(List.of(authors.get(1)));

        List<AuthorResponseDto> authorResponseDtos2 = authorServiceImpl.searchAuthors("Test Author", true);

        Assertions.assertEquals(1, authorResponseDtos2.size());
        Assertions.assertTrue(authors.get(1).getArchived());
    }

    @Test
    void updateAuthorTest() {
        Author author = TestUtils.author(false);
        AuthorRequestDto authorRequestDto = TestUtils.authorRequestDto();
        authorRequestDto.setName("Updated Author");

        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        AuthorResponseDto authorResponseDto = authorServiceImpl.updateAuthor(author.getId(), authorRequestDto);

        Assertions.assertEquals(authorResponseDto.getName(), authorRequestDto.getName());
        Assertions.assertFalse(authorResponseDto.getArchived());
    }

    @Test
    void deleteAuthorTest() {
        Author author = TestUtils.author(false);

        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        AuthorResponseDto authorResponseDto = authorServiceImpl.deleteAuthor(author.getId());

        Assertions.assertEquals(author.getName(), authorResponseDto.getName());
        Assertions.assertTrue(authorResponseDto.getArchived());
    }


}
