package com.iceb.library.repository;

import com.iceb.library.TestUtils;
import com.iceb.library.entity.Book;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class TestRepositoryHelper {

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected PublisherRepository publisherRepository;

    @Autowired
    protected TopicRepository topicRepository;

    protected Book bookTest;
    protected Genre genreTest;
    protected Author authorTest;
    protected Publisher publisherTest;
    protected Topic topicTest;

    @BeforeEach
    public void setUp() {
        cleanUp();
        genreTest = TestUtils.genre(false);
        genreTest = genreRepository.save(genreTest);

        authorTest = TestUtils.author(false);
        authorTest= authorRepository.save(authorTest);

        publisherTest = TestUtils.publisher(false);
        publisherTest = publisherRepository.save(publisherTest);

        topicTest = TestUtils.topic(false);
        topicTest = topicRepository.save(topicTest);

        bookTest = TestUtils.book(false);
        bookTest.setGenres(Arrays.asList(genreTest));
        bookTest.setAuthors(Arrays.asList(authorTest));
        bookTest.setPublisher(publisherTest);
        bookTest.setTopics(Arrays.asList(topicTest));

        bookTest = bookRepository.save(bookTest);
    }

    @AfterEach
    public void cleanUp() {
        bookRepository.deleteAll();
        genreRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
        topicRepository.deleteAll();
    }
}
