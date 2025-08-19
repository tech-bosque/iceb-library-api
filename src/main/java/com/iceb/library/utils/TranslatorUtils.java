package com.iceb.library.utils;

import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.borrow.BorrowItemRequestDto;
import com.iceb.library.dto.borrow.BorrowItemResponseDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.customer.CustomerResponseDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.dto.topic.TopicResponseDto;
import com.iceb.library.entity.Author;
import com.iceb.library.entity.Book;
import com.iceb.library.entity.Borrow;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.entity.Customer;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TranslatorUtils {

    public static AuthorResponseDto authorToAuthorResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .archived(author.getArchived())
                .build();
    }

    public static Author authorResponseDtoToAuthor(AuthorResponseDto authorResponseDto) {
        return Author.builder()
                .id(authorResponseDto.getId())
                .name(authorResponseDto.getName())
                .archived(authorResponseDto.getArchived())
                .build();
    }

    public static BookResponseDto bookToBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .edition(book.getEdition())
                .language(book.getLanguage())
                .publicationYear(book.getPublicationYear())
                .pages(book.getPages())
                .observation(book.getObservation())
                .donation(book.getDonation())
                .assetNumber(book.getAssetNumber())
                .isbn(book.getIsbn())
                .urlCover(book.getUrlCover())
                .archived(book.getArchived())
                .publisherId(book.getPublisher() != null ? book.getPublisher().getId() : null)
                .authorsId(book.getAuthors() != null
                        ? book.getAuthors().stream()
                        .map(Author::getId)
                        .collect(Collectors.toList())
                        : null)
                .genresId(book.getGenres() != null
                        ? book.getGenres().stream()
                        .map(Genre::getId)
                        .collect(Collectors.toList())
                        : null)
                .topicsId(book.getTopics() != null
                        ? book.getTopics().stream()
                        .map(Topic::getId)
                        .collect(Collectors.toList())
                        : null)
                .available(book.getAvailable())
                .build();
    }

    public static BorrowResponseDto borrowToBorrowResponseDto(Borrow borrow) {
        List<BorrowItemResponseDto> borrowItemResponseDtos = borrow.getBorrowItems().stream()
                .map(TranslatorUtils::borrowItemToBorrowItemResponseDto)
                .collect(Collectors.toList());

        return BorrowResponseDto.builder()
                .id(borrow.getId())
                .customerId(borrow.getCustomer().getId())
                .borrowDate(borrow.getBorrowDate())
                .borrowItems(borrowItemResponseDtos)
                .build();
    }

    public static BorrowItemResponseDto borrowItemToBorrowItemResponseDto(BorrowItem borrowItem) {
        return BorrowItemResponseDto.builder()
                .id(borrowItem.getId())
                .returnDate(borrowItem.getReturnDate())
                .actualReturnDate(borrowItem.getActualReturnDate())
                .bookId(borrowItem.getBookId())
                .build();
    }

    public static Customer customerResponseDtoToCustomer(CustomerResponseDto customerResponseDto) {
        return Customer.builder()
                .id(customerResponseDto.getId())
                .name(customerResponseDto.getName())
                .email(customerResponseDto.getEmail())
                .phone(customerResponseDto.getPhone())
                .role(customerResponseDto.getRole())
                .archived(customerResponseDto.getArchived())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .role(customer.getRole())
                .archived(customer.getArchived())
                .build();
    }

    public static BorrowItem borrowItemRequestDtoToBorrowItem(BorrowItemRequestDto borrowItemRequestDto, BookResponseDto book) {
        return BorrowItem.builder()
                .returnDate(borrowItemRequestDto.getReturnDate())
                .bookId(book.getId())
                .build();
    }

    public static GenreResponseDto genreToGenreResponseDto(Genre genre) {
        return GenreResponseDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .archived(genre.getArchived())
                .build();
    }

    public static Genre genreResponseDtoToGenre(GenreResponseDto genreResponseDto) {
        return Genre.builder()
                .id(genreResponseDto.getId())
                .name(genreResponseDto.getName())
                .archived(genreResponseDto.getArchived())
                .build();
    }

    public static PublisherResponseDto publisherToPublisherResponseDto(Publisher publisher) {
        return PublisherResponseDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .archived(publisher.getArchived())
                .build();
    }

    public static Publisher publisherResponseDtoToPublisher(PublisherResponseDto publisherResponseDto) {
        return Publisher.builder()
                .id(publisherResponseDto.getId())
                .name(publisherResponseDto.getName())
                .archived(publisherResponseDto.getArchived())
                .build();
    }

    public static TopicResponseDto topicToTopicResponseDto(Topic topic) {
        return TopicResponseDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .archived(topic.getArchived())
                .build();
    }

    public static Topic topicResponseDtoToTopic(TopicResponseDto topicResponseDto) {
        return Topic.builder()
                .id(topicResponseDto.getId())
                .name(topicResponseDto.getName())
                .archived(topicResponseDto.getArchived())
                .build();
    }
}
