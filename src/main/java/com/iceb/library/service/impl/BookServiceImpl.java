package com.iceb.library.service.impl;

import com.iceb.library.dto.BookRequestDto;
import com.iceb.library.dto.BookResponseDto;
import com.iceb.library.dto.BookSearchDto;
import com.iceb.library.entity.Book;
import com.iceb.library.exception.BookNotFoundException;
import com.iceb.library.repository.BookRepository;
import com.iceb.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        logger.info("Creating book");
        logger.debug("Creating book with details: {}", bookRequestDto);

        Book book = Book.builder()
                .name(bookRequestDto.getName())
                .authors(bookRequestDto.getAuthors())
                .publisher(bookRequestDto.getPublisher())
                .genres(bookRequestDto.getGenres())
                .topics(bookRequestDto.getTopics())
                .edition(bookRequestDto.getEdition())
                .language(bookRequestDto.getLanguage())
                .year(bookRequestDto.getYear())
                .pages(bookRequestDto.getPages())
                .observation(bookRequestDto.getObservation())
                .donation(bookRequestDto.getDonation())
                .assetNumber(bookRequestDto.getAssetNumber())
                .isbn(bookRequestDto.getIsbn())
                .urlCover(bookRequestDto.getUrlCover())
                .archived(false)
                .build();
        Book savedBook = bookRepository.save(book);

        logger.info("Created book successfully");
        logger.debug("Created book: {}", savedBook);

        return mapToResponseDto(savedBook, Arrays.asList(false));
    }

    @Override
    public BookResponseDto getBookById(UUID id) {
        logger.info("Fetching book by ID");
        logger.debug("Fetching book with ID: {}", id);

        Book book = findBookById(id);
        BookResponseDto responseDto = mapToResponseDto(book, Arrays.asList(false));

        logger.debug("Fetched book: {}", responseDto);
        logger.info("Fetched book by ID successfully");
        return responseDto;
    }

    @Override
    public List<BookResponseDto> searchBooks(BookSearchDto bookSearchDto) {
        logger.info("Fetching books with filters");
        logger.debug("Fetching books with filters: {}", bookSearchDto);

        List<Book> books = bookRepository.searchBooks(bookSearchDto);

        List<BookResponseDto> responseDtos = books.stream()
                .map(book -> mapToResponseDto(book, bookSearchDto.getArchived()))
                .collect(Collectors.toList());

        logger.debug("Fetched books: {}", responseDtos);
        logger.info("Fetched books with filters successfully");
        return responseDtos;
    }

    @Override
    public BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto) {
        logger.info("Updating book");
        logger.debug("Updating book with ID: {} with details: {}", id, bookRequestDto);

        Book existingBook = findBookById(id);
        existingBook.setName(bookRequestDto.getName());
        Book updatedBook = bookRepository.save(existingBook);

        logger.info("Updated book successfully");
        logger.debug("Updated book: {}", updatedBook);

        return mapToResponseDto(updatedBook, Arrays.asList(false));
    }

    @Override
    public BookResponseDto deleteBook(UUID id) {
        logger.info("Archiving book");
        logger.debug("Archiving book with ID: {}", id);

        Book book = findBookById(id);
        book.setArchived(true);
        Book archivedBook = bookRepository.save(book);

        logger.info("Archived book successfully");
        logger.debug("Archived book with ID: {}", id);

        return mapToResponseDto(archivedBook, Arrays.asList(false));
    }

    private Book findBookById(UUID id) {
        logger.info("Finding book by ID");
        logger.debug("Finding book with ID: {}", id);

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("The book with the provided ID does not exist"));
    }

    private BookResponseDto mapToResponseDto(Book book,  List<Boolean> archived) {
        BookResponseDto.BookResponseDtoBuilder builder = BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .edition(book.getEdition())
                .language(book.getLanguage())
                .year(book.getYear())
                .pages(book.getPages())
                .observation(book.getObservation())
                .donation(book.getDonation())
                .assetNumber(book.getAssetNumber())
                .isbn(book.getIsbn())
                .urlCover(book.getUrlCover())
                .publisher(book.getPublisher())
                .authors(book.getAuthors())
                .genres(book.getGenres())
                .topics(book.getTopics());
        
        if (archived.contains(true)) {
            builder.archived(book.getArchived());
        }

        return builder.build();
    }
}
