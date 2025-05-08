package com.iceb.library.repository;

import com.iceb.library.TestUtils;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Book;
import com.iceb.library.entity.Borrow;
import com.iceb.library.entity.Customer;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import com.iceb.library.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class TestRepositoryHelper {

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected PublisherRepository publisherRepository;

    @Autowired
    protected TopicRepository topicRepository;

    @Autowired
    protected BorrowRepository borrowRepository;

    protected Book bookTest;
    protected Customer customerTest;
    protected Genre genreTest;
    protected Author authorTest;
    protected Publisher publisherTest;
    protected Topic topicTest;
    protected Borrow borrowTest;

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
        bookTest.setGenres(List.of(genreTest));
        bookTest.setAuthors(List.of(authorTest));
        bookTest.setPublisher(publisherTest);
        bookTest.setTopics(List.of(topicTest));

        bookTest = bookRepository.save(bookTest);

        customerTest = TestUtils.customer(false);
        customerTest.setRole(Role.ADMIN);
        customerTest = customerRepository.save(customerTest);

        borrowTest = TestUtils.borrow();
        borrowTest.setCustomer(customerTest);
        borrowTest = borrowRepository.save(borrowTest);
    }

    @AfterEach
    public void cleanUp() {
        bookRepository.deleteAll();
        genreRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
        topicRepository.deleteAll();
        customerRepository.deleteAll();
        borrowRepository.deleteAll();
    }
}
