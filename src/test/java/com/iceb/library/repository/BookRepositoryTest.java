package com.iceb.library.repository;

import com.iceb.library.dto.BookSearchDto;
import com.iceb.library.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;


public class BookRepositoryTest extends TestRepositoryHelper{


    @Test
    void findBookByIdTest() {
        Book savedBook = bookRepository.findById(bookTest.getId()).orElse(null);
        Assertions.assertNotNull(savedBook);
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksWithoutParamsTest() {

        Book secondBook = Book.builder()
                .id(null)
                .name("Second Book")
                .edition("Second Edition")
                .language("English")
                .year(2021)
                .pages(200)
                .observation("Second Observation")
                .donation(false)
                .assetNumber("Second Asset Number")
                .isbn("Second ISBN")
                .urlCover("Second URL Cover")
                .archived(false)
                .build();

        bookRepository.save(secondBook);

        BookSearchDto bookSearchDto = BookSearchDto.builder().build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());
        Assertions.assertEquals(2, savedBooks.size());
    }

    @Test
    void searchBooksByTermTest() {
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .search("Test term")
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksByGenreTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .genre(Arrays.asList(bookTest.getGenres().getFirst().getName()))
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksByTopicTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .topic(Arrays.asList(bookTest.getTopics().getFirst().getName()))
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksByLanguageTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .language(Arrays.asList(bookTest.getLanguage()))
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksByYearTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .year(bookTest.getYear())
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

    @Test
    void searchBooksByDonationTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .donation(bookTest.getDonation())
                .build();

        List<Book> savedBooks = bookRepository.searchBooks(bookSearchDto);
        Assertions.assertNotNull(savedBooks);
        Assertions.assertFalse(savedBooks.isEmpty());

        Book savedBook = savedBooks.getFirst();
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(bookTest);
    }

}
