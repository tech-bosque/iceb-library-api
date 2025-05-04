package com.iceb.library.repository;

import com.iceb.library.entity.Book;
import com.iceb.library.dto.BookSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("""
        SELECT b FROM Book b
        LEFT JOIN b.authors a
        LEFT JOIN b.publisher p
        LEFT JOIN b.genres g
        LEFT JOIN b.topics t
        WHERE (
            :#{#params.search} IS NULL
            OR LOWER(b.name) LIKE LOWER(CONCAT('%', :#{#params.name}, '%'))
            OR LOWER(a.name) LIKE LOWER(CONCAT('%', :#{#params.author}, '%'))
            OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#params.publisher}, '%'))
        )
        AND (:#{#params.authors} IS EMPTY OR a.id IN :#{#params.authors})
        AND (:#{#params.publisher} IS EMPTY OR p.id IN :#{#params.publisher})
        AND (:#{#params.genres} IS EMPTY OR g.id IN :#{#params.genres})
        AND (:#{#params.topics} IS EMPTY OR t.id IN :#{#params.topics})
        AND (:#{#params.language} IS EMPTY OR b.language IN :#{#params.language})
        AND (:#{#params.year} IS NULL OR b.year = :#{#params.year})
        AND (:#{#params.donation} IS NULL OR b.donation = :#{#params.donation})
        AND (:#{#params.archived} IS NULL OR :#{#params.archived} = TRUE OR b.archived = :#{#params.archived})
    """)
    List<Book> searchBooks(@Param("params") BookSearchDto params);
}
