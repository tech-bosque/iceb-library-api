package com.iceb.library.repository;

import com.iceb.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("SELECT a FROM Author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) AND (:archived = true OR a.archived = :archived)")
    List<Author> findSimilarNames(@Param("name") String name, @Param("archived") boolean archived);

    @Query("SELECT a FROM Author a WHERE :archived = true OR a.archived = :archived")
    List<Author> findAllAuthors(@Param("archived") boolean archived);
}
