package com.iceb.library.repository;

import com.iceb.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query("SELECT g FROM Genre g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')) AND (:archived = true OR g.archived = :archived)")
    List<Genre> findSimilarNames(@Param("name") String name, @Param("archived") boolean archived);

    @Query("SELECT g FROM Genre g WHERE :archived = true OR g.archived = :archived")
    List<Genre> findAllGenres(@Param("archived") boolean archived);
}
