package com.iceb.library;

import com.iceb.library.dto.AuthorRequestDto;
import com.iceb.library.dto.AuthorResponseDto;
import com.iceb.library.dto.BookRequestDto;
import com.iceb.library.dto.BookResponseDto;
import com.iceb.library.dto.BookSearchDto;
import com.iceb.library.dto.CustomerRequestDto;
import com.iceb.library.dto.CustomerResponseDto;
import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.dto.GenreRequestDto;
import com.iceb.library.dto.GenreResponseDto;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.dto.RolesResponseDto;
import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Book;
import com.iceb.library.entity.Customer;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import com.iceb.library.enums.Role;

import java.util.Arrays;
import java.util.UUID;

public class TestUtils {


    public static Publisher publisher(Boolean archived) {
        return Publisher.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"))
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"))
                .name("Test Publisher")
                .archived(archived)
                .build();
    }

    public static Genre genre(Boolean archived) {
        return Genre.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"))
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174004"))
                .name("Test Genre")
                .archived(archived)
                .build();
    }

    public static Topic topic(Boolean archived) {
        return Topic.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174005"))
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174006"))
                .name("Test Topic")
                .archived(archived)
                .build();
    }

    public static Author author(Boolean archived) {
        return Author.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174007"))
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174008"))
                .name("Test Author")
                .archived(archived)
                .build();
    }

    public static Book book(Boolean archived) {
        return Book.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174009"))
                .name("Test Book")
                .authors(Arrays.asList(author(false)))
                .publisher(publisher(false))
                .genres(Arrays.asList(genre(false)))
                .topics(Arrays.asList(topic(false)))
                .edition("Test Edition")
                .language("Test Language")
                .year(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
                .archived(archived)
                .build();
    }

    public static BookRequestDto bookRequestDto() {
        return BookRequestDto.builder()
                .name("Test Book")
                .publisher(publisher(false))
                .authors(Arrays.asList(author(false)))
                .genres(Arrays.asList(genre(false)))
                .topics(Arrays.asList(topic(false)))
                .edition("Test Edition")
                .language("Test Language")
                .year(2023)
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174010"))
                .name("Test Book")
                .genres(Arrays.asList(genre(false)))
                .topics(Arrays.asList(topic(false)))
                .edition("Test Edition")
                .language("Test Language")
                .year(2023)
                .pages(100)
                .observation("Test Observation")
                .donation(true)
                .assetNumber("12345")
                .isbn("123-4567890123")
                .urlCover("http://testcover.com")
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
                .year(2023)
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
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174011"))
                .name("Test Customer")
                .email("test@test.com")
                .password("test")
                .phone("1234567890")
                .role(Role.ROLE_ADMIN)
                .archived(archived)
                .build();
    }

    public static CustomerRequestDto customerRequestDto() {
        return CustomerRequestDto.builder()
                .name("Test Customer")
                .email("test@test.com")
                .password("test")
                .phone("1234567890")
                .role(Role.ROLE_ADMIN)
                .build();
    }

    public static CustomerResponseDto customerResponseDto(Boolean archived) {
        return CustomerResponseDto.builder()
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174011"))
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
                .email("test@test.com")
                .phone("1234567890")
                .role(Arrays.asList(Role.ROLE_LIBRARIAN).toString())
                .archived(false)
                .build();
    }

    public static RolesResponseDto rolesResponseDto() {
        return RolesResponseDto.builder()
                .roles(Arrays.asList(Role.values()))
                .build();
    }

}
