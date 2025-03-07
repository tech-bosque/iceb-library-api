package com.iceb.library.repository;

import com.iceb.library.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorRepositoryTest extends TestRepositoryHelper{

    @Test
    void findAuthorByIdTest() {
        Author savedAuthor = authorRepository.findById(authorTest.getId()).orElse(null);
        Assertions.assertNotNull(savedAuthor);
        assertThat(savedAuthor).usingRecursiveComparison().isEqualTo(authorTest);
    }

    @Test
    void findAllAuthorsTest() {
        Author secondAuthor = Author.builder()
                .id(null)
                .name("Second Author")
                .archived(false)
                .build();

        authorRepository.save(secondAuthor);

        List<Author> savedAuthors = authorRepository.findAllAuthors(false);
        Assertions.assertNotNull(savedAuthors);
        Assertions.assertFalse(savedAuthors.isEmpty());
        Assertions.assertEquals(2, savedAuthors.size());
    }

    @Test
    void findSimilarNamesTest() {
        List<Author> savedAuthors = authorRepository.findSimilarNames(authorTest.getName(), false);
        Assertions.assertNotNull(savedAuthors);
        Assertions.assertFalse(savedAuthors.isEmpty());
        Assertions.assertEquals(1, savedAuthors.size());
    }
}
