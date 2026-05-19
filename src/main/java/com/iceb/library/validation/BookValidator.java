package com.iceb.library.validation;

import com.iceb.library.entity.Author;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import com.iceb.library.service.AuthorService;
import com.iceb.library.service.GenreService;
import com.iceb.library.service.PublisherService;
import com.iceb.library.service.TopicService;
import com.iceb.library.utils.TranslatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class BookValidator {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PublisherService publisherService;

    public List<Author> validateAuthorsId(List<UUID> authorsId) {
        if (authorsId == null || authorsId.isEmpty()) {
            return Collections.emptyList();
        }
        return authorsId.stream()
                .map(authorId -> TranslatorUtils.authorResponseDtoToAuthor(authorService.getAuthorById(authorId)))
                .toList();
    }

    public List<Genre> validateGenresId(List<UUID> genresId) {
        if (genresId == null || genresId.isEmpty()) {
            return Collections.emptyList();
        }
        return genresId.stream()
                .map(genreId -> TranslatorUtils.genreResponseDtoToGenre(genreService.getGenreById(genreId)))
                .toList();
    }

    public List<Topic> validateTopicsId(List<UUID> topicsId) {
        if (topicsId == null || topicsId.isEmpty()) {
            return Collections.emptyList();
        }
        return topicsId.stream()
                .map(topicId -> TranslatorUtils.topicResponseDtoToTopic(topicService.getTopicById(topicId)))
                .toList();
    }

    public Publisher validatePublisherId(UUID publisherId) {
        if (publisherId == null) {
            return null;
        }
        return TranslatorUtils.publisherResponseDtoToPublisher(publisherService.getPublisherById(publisherId));
    }
}
