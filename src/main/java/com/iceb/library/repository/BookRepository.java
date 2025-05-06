package com.iceb.library.repository;

import com.iceb.library.entity.Book;
import com.iceb.library.dto.BookSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b " +
            "LEFT JOIN b.authors a " +
            "LEFT JOIN b.publisher p " +
            "LEFT JOIN b.genres g " +
            "LEFT JOIN b.topics t " +
            "WHERE (:#{#params.name} IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :#{#params.name}, '%'))) " +
            "AND (:#{#params.author} IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :#{#params.author}, '%'))) " +
            "AND (:#{#params.publisher} IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#params.publisher}, '%'))) " +
            "AND (:#{#params.genre} IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :#{#params.genre}, '%'))) " +
            "AND (:#{#params.topic} IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :#{#params.topic}, '%'))) " +
            "AND (:#{#params.edition} IS NULL OR LOWER(b.edition) LIKE LOWER(CONCAT('%', :#{#params.edition}, '%'))) " +
            "AND (:#{#params.language} IS NULL OR LOWER(b.language) LIKE LOWER(CONCAT('%', :#{#params.language}, '%'))) " +
            "AND (:#{#params.year} IS NULL OR b.year = :#{#params.year}) " +
            "AND (:#{#params.pages} IS NULL OR b.pages = :#{#params.pages}) " +
            "AND (:#{#params.observation} IS NULL OR LOWER(b.observation) LIKE LOWER(CONCAT('%', :#{#params.observation}, '%'))) " +
            "AND (:#{#params.donation} IS NULL OR b.donation = :#{#params.donation}) " +
            "AND (:#{#params.assetNumber} IS NULL OR LOWER(b.assetNumber) LIKE LOWER(CONCAT('%', :#{#params.assetNumber}, '%'))) " +
            "AND (:#{#params.isbn} IS NULL OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :#{#params.isbn}, '%'))) " +
            "AND (:#{#params.urlCover} IS NULL OR LOWER(b.urlCover) LIKE LOWER(CONCAT('%', :#{#params.urlCover}, '%'))) " +
            "AND (:#{#params.archived} IS NULL OR :#{#params.archived} = TRUE OR b.archived = :#{#params.archived})")
    List<Book> searchBooks(@Param("params") BookSearchDto params);
}
