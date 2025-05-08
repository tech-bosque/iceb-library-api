package com.iceb.library.validation;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.dto.topic.TopicResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import com.iceb.library.entity.Genre;
import com.iceb.library.service.AuthorService;
import com.iceb.library.service.GenreService;
import com.iceb.library.service.PublisherService;
import com.iceb.library.service.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookValidatorTest {

    @InjectMocks
    private BookValidator bookValidator;

    @Mock
    private GenreService genreService;

    @Mock
    private TopicService topicService;

    @Mock
    private PublisherService publisherService;

    @Mock
    private AuthorService authorService;

    @Test
    void validateAuthorsIdTest() {

        AuthorResponseDto authorResponseDto = TestUtils.authorResponseDto(false);
        List<Author> authors = List.of(TestUtils.author(false));

        when(authorService.getAuthorById(authorResponseDto.getId())).thenReturn(authorResponseDto);

        List<Author> result = bookValidator.validateAuthorsId(List.of(authorResponseDto.getId()));

        assertThat(authors).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void validateGenresIdTest() {
        GenreResponseDto genreResponseDto = TestUtils.genreResponseDto(false);
        List<Genre> genres = List.of(TestUtils.genre(false));

        when(genreService.getGenreById(genreResponseDto.getId())).thenReturn(genreResponseDto);

        List<Genre> result = bookValidator.validateGenresId(List.of(genreResponseDto.getId()));

        assertThat(genres).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void validateTopicsIdTest() {
        TopicResponseDto topicResponseDto = TestUtils.topicResponseDto(false);
        List<Topic> topics = List.of(TestUtils.topic(false));

        when(topicService.getTopicById(topicResponseDto.getId())).thenReturn(topicResponseDto);

        List<Topic> result = bookValidator.validateTopicsId(List.of(topicResponseDto.getId()));

        assertThat(topics).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void validatePublisherIdTest() {
        PublisherResponseDto publisherResponseDto = TestUtils.publisherResponseDto(false);
        Publisher publisher = TestUtils.publisher(false);

        when(publisherService.getPublisherById(publisherResponseDto.getId())).thenReturn(publisherResponseDto);

        Publisher result = bookValidator.validatePublisherId(publisherResponseDto.getId());

        assertThat(publisher).usingRecursiveComparison().isEqualTo(result);
    }
}
