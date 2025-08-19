package com.iceb.library;

import com.iceb.library.dto.RolesResponseDto;
import com.iceb.library.dto.author.AuthorRequestDto;
import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.dto.book.BookRequestDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.book.BookSearchDto;
import com.iceb.library.dto.borrow.BorrowItemRequestDto;
import com.iceb.library.dto.borrow.BorrowItemResponseDto;
import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.dto.customer.CustomerRequestDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.customer.CustomerSearchDto;
import com.iceb.library.dto.genre.GenreRequestDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.dto.publisher.PublisherRequestDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.dto.topic.TopicRequestDto;
import com.iceb.library.dto.topic.TopicResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Book;
import com.iceb.library.entity.Borrow;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.entity.Customer;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import com.iceb.library.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    private static final UUID PUBLISHER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
    private static final UUID AUTHOR_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174007");
    private static final UUID GENRE_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174003");
    private static final UUID TOPIC_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174005");
    private static final UUID CUSTOMER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174011");
    private static final UUID BORROW_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174013");
    private static final UUID BORROW_ITEM_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174012");
    private static final UUID BOOK_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174009");


    public static Publisher publisher(Boolean archived) {
        return Publisher.builder()
                .id(PUBLISHER_ID)
                .name("Test Publisher")
                .archived(archived)
                .build();
    }

    public static PublisherRequestDto publisherRequestDto() {
        return PublisherRequestDto.builder()
                .name("Test Publisher")
                .build();
    }

    public static PublisherResponseDto publisherResponseDto(Boolean archived) {
        return PublisherResponseDto.builder()
                .id(PUBLISHER_ID)
                .name("Test Publisher")
                .archived(archived)
                .build();
    }

    public static Genre genre(Boolean archived) {
        return Genre.builder()
                .id(GENRE_ID)
                .name("Test Genre")
                .archived(archived)
                .build();
    }

    public static GenreRequestDto genreRequestDto() {
        return GenreRequestDto.builder()
                .name("Test Genre")
                .build();
    }

    public static GenreResponseDto genreResponseDto(Boolean archived) {
        return GenreResponseDto.builder()
                .id(GENRE_ID)
                .name("Test Genre")
                .archived(archived)
                .build();
    }

    public static Topic topic(Boolean archived) {
        return Topic.builder()
                .id(TOPIC_ID)
                .name("Test Topic")
                .archived(archived)
                .build();
    }

    public static TopicRequestDto topicRequestDto() {
        return TopicRequestDto.builder()
                .name("Test Topic")
                .build();
    }

    public static TopicResponseDto topicResponseDto(Boolean archived) {
        return TopicResponseDto.builder()
                .id(TOPIC_ID)
                .name("Test Topic")
                .archived(archived)
                .build();
    }

    public static Author author(Boolean archived) {
        return Author.builder()
                .id(AUTHOR_ID)
                .name("Test Author")
                .archived(archived)
                .build();
    }

    public static AuthorRequestDto authorRequestDto() {
        return AuthorRequestDto.builder()
                .name("Test Author")
                .build();
    }

    public static AuthorResponseDto authorResponseDto(Boolean archived) {
        return AuthorResponseDto.builder()
                .id(AUTHOR_ID)
                .name("Test Author")
                .archived(archived)
                .build();
    }

    public static Book book(Boolean archived) {
        return Book.builder()
                .id(BOOK_ID)
                .name("Test Book")
                .authors(List.of(author(false)))
                .publisher(publisher(false))
                .genres(List.of(genre(false)))
                .topics(List.of(topic(false)))
                .edition("Test Edition")
                .language("Test Language")
                .publicationYear(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
                .available(true)
                .archived(archived)
                .build();
    }

    public static BookRequestDto bookRequestDto() {
        return BookRequestDto.builder()
                .name("Test Book")
                .publisherId(PUBLISHER_ID)
                .authorsId(List.of(AUTHOR_ID))
                .genresId(List.of(GENRE_ID))
                .topicsId(List.of(TOPIC_ID))
                .edition("Test Edition")
                .language("Test Language")
                .publicationYear(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
                .build();
    }

    public static BookResponseDto bookResponseDto(Boolean archived) {
        return BookResponseDto.builder()
                .id(BOOK_ID)
                .name("Test Book")
                .genresId(List.of(GENRE_ID))
                .topicsId(List.of(TOPIC_ID))
                .edition("Test Edition")
                .language("Test Language")
                .publicationYear(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
                .available(true)
                .archived(archived)
                .build();
    }

    public static BookSearchDto bookSearchDto() {
        return BookSearchDto.builder()
                .name("Test Book")
                .author("Test Author")
                .publisher("Test Publisher")
                .genre("Test Genre")
                .topic("Test Topic")
                .edition("Test Edition")
                .language("Test Language")
                .publicationYear(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
                .archived(false)
                .build();
    }

    public static Customer customer(Boolean archived) {
        return Customer.builder()
                .id(CUSTOMER_ID)
                .name("Test Customer")
                .email("test@example.com")
                .password("test")
                .phone("1234567890")
                .role(Role.ROLE_ADMIN)
                .archived(archived)
                .build();
    }

    public static CustomerRequestDto customerRequestDto() {
        return CustomerRequestDto.builder()
                .name("Test Customer")
                .email("test@example.com")
                .password("test")
                .phone("1234567890")
                .role(Role.ROLE_ADMIN)
                .build();
    }

    public static CustomerResponseDto customerResponseDto(Boolean archived) {
        return CustomerResponseDto.builder()
                .id(CUSTOMER_ID)
                .name("Test Customer")
                .email("test@example.com")
                .phone("1234567890")
                .role(Role.ROLE_ADMIN)
                .archived(archived)
                .build();
    }

    public static CustomerSearchDto customerSearchDto() {
        return CustomerSearchDto.builder()
                .name("Test Customer")
                .email("test@example.com")
                .phone("1234567890")
                .role(List.of(Role.ROLE_LIBRARIAN).toString())
                .archived(false)
                .build();
    }

    public static RolesResponseDto rolesResponseDto() {
        return RolesResponseDto.builder()
                .roles(List.of(Role.values()))
                .build();
    }

    public static BorrowItemResponseDto borrowItemResponseDto() {
        return BorrowItemResponseDto.builder()
                .id(BORROW_ITEM_ID)
                .returnDate(LocalDate.now())
                .actualReturnDate(LocalDate.now())
                .bookId(BOOK_ID)
                .build();
    }

    public static BorrowResponseDto borrowResponseDto() {
        return BorrowResponseDto.builder()
                .id(BOOK_ID)
                .customerId(customer(false).getId())
                .borrowDate(LocalDate.now())
                .borrowItems(List.of(borrowItemResponseDto()))
                .build();
    }

    public static BorrowItemRequestDto borrowItemRequestDto() {
        return BorrowItemRequestDto.builder()
                .bookId(BOOK_ID)
                .build();
    }

    public static BorrowRequestDto borrowRequestDto() {
        return BorrowRequestDto.builder()
                .customerId(CUSTOMER_ID)
                .borrowDate(LocalDate.now())
                .borrowItems(List.of(borrowItemRequestDto()))
                .build();
    }

    public static BorrowSearchDto borrowSearchDto() {
        return BorrowSearchDto.builder()
                .customerId(CUSTOMER_ID)
                .borrowDate("2025-02-21")
                .build();
    }

    public static BorrowItemUpdateDto borrowItemUpdateDto() {
        return BorrowItemUpdateDto.builder()
                .borrowItemId(BORROW_ITEM_ID)
                .bookId(BOOK_ID)
                .actualReturnDate(LocalDate.now())
                .build();
    }

    public static BorrowItemReturnDto borrowItemReturnDto() {
        return BorrowItemReturnDto.builder()
                .borrowItemId(BORROW_ITEM_ID)
                .borrowId(BORROW_ID)
                .actualReturnDate(LocalDate.now())
                .build();
    }

    public static BorrowItem borrowItem() {
        return BorrowItem.builder()
                .id(BORROW_ITEM_ID)
                .returnDate(LocalDate.now())
                .actualReturnDate(LocalDate.now())
                .bookId(BOOK_ID)
                .build();
    }

    public static Borrow borrow() {
        return Borrow.builder()
                .id(BORROW_ID)
                .customer(customer(false))
                .borrowDate(LocalDate.now())
                .borrowItems(List.of(borrowItem()))
                .build();
    }

}
