package com.iceb.library.service.impl;

import com.iceb.library.dto.book.BookRequestDto;
import com.iceb.library.dto.book.BookResponseDto;
import com.iceb.library.dto.book.BookSearchDto;
import com.iceb.library.entity.Book;
import com.iceb.library.exception.BookNotFoundException;
import com.iceb.library.repository.BookRepository;
import com.iceb.library.service.AuthorService;
import com.iceb.library.service.BookService;
import com.iceb.library.utils.TranslatorUtils;
import com.iceb.library.validation.BookValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        logger.info("Creating book");
        logger.debug("Creating book with details: {}", bookRequestDto);

        Book book = Book.builder()
                .name(bookRequestDto.getName())
                .authors(bookValidator.validateAuthorsId(bookRequestDto.getAuthorsId()))
                .publisher(bookValidator.validatePublisherId(bookRequestDto.getPublisherId()))
                .genres(bookValidator.validateGenresId(bookRequestDto.getGenresId()))
                .topics(bookValidator.validateTopicsId(bookRequestDto.getTopicsId()))
                .edition(bookRequestDto.getEdition())
                .language(bookRequestDto.getLanguage())
                .publicationYear(bookRequestDto.getPublicationYear())
                .pages(bookRequestDto.getPages())
                .observation(bookRequestDto.getObservation())
                .donation(bookRequestDto.getDonation())
                .assetNumber(bookRequestDto.getAssetNumber())
                .isbn(bookRequestDto.getIsbn())
                .urlCover(bookRequestDto.getUrlCover())
                .available(true)
                .archived(false)
                .build();
        Book savedBook = bookRepository.save(book);

        logger.info("Created book successfully");
        logger.debug("Created book: {}", savedBook);

        return TranslatorUtils.bookToBookResponseDto(savedBook);
    }

    @Override
    public BookResponseDto getBookById(UUID id) {
        logger.info("Fetching book by ID");
        logger.debug("Fetching book with ID: {}", id);

        Book book = findBookById(id);
        BookResponseDto responseDto = TranslatorUtils.bookToBookResponseDto(book);

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
                .map(TranslatorUtils::bookToBookResponseDto)
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
        existingBook.setAuthors(bookValidator.validateAuthorsId(bookRequestDto.getAuthorsId()));
        existingBook.setPublisher(bookValidator.validatePublisherId(bookRequestDto.getPublisherId()));
        existingBook.setGenres(bookValidator.validateGenresId(bookRequestDto.getGenresId()));
        existingBook.setTopics(bookValidator.validateTopicsId(bookRequestDto.getTopicsId()));
        existingBook.setEdition(bookRequestDto.getEdition());
        existingBook.setLanguage(bookRequestDto.getLanguage());
        existingBook.setPublicationYear(bookRequestDto.getPublicationYear());
        existingBook.setPages(bookRequestDto.getPages());
        existingBook.setObservation(bookRequestDto.getObservation());
        existingBook.setDonation(bookRequestDto.getDonation());
        existingBook.setAssetNumber(bookRequestDto.getAssetNumber());
        existingBook.setIsbn(bookRequestDto.getIsbn());
        existingBook.setUrlCover(bookRequestDto.getUrlCover());
        Book updatedBook = bookRepository.save(existingBook);

        logger.info("Updated book successfully");
        logger.debug("Updated book: {}", updatedBook);

        return TranslatorUtils.bookToBookResponseDto(updatedBook);
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

        return TranslatorUtils.bookToBookResponseDto(archivedBook);
    }

    @Override
    public Boolean isBookAvailable(UUID id) {
        logger.info("Checking book availability");
        logger.debug("Checking book availability with ID: {}", id);

        Book book = findBookById(id);

        logger.debug("Checking book availability: {}", book);
        logger.info("Book availability checked successfully");
        return book.getAvailable();
    }

    @Override
    public void updateBookAvailability(UUID id, Boolean available) {
        logger.info("Updating book availability");
        logger.debug("Updating book availability with ID: {}", id);

        Book existingBook = findBookById(id);
        existingBook.setAvailable(available);

        Book updatedBook = bookRepository.save(existingBook);

        logger.info("Updated book availability successfully");
        logger.debug("Updated book availability: {}", updatedBook);

    }

    private Book findBookById(UUID id) {
        logger.info("Finding book by ID");
        logger.debug("Finding book with ID: {}", id);

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("The book with the provided ID does not exist"));
    }

}
