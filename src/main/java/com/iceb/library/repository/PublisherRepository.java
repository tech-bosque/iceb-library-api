package com.iceb.library.repository;

import com.iceb.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    @Query("SELECT p FROM Publisher p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.archived IN :archived")
    List<Publisher> findSimilarNames(@Param("name") String name, @Param("archived") List<Boolean> archived);

    @Query("SELECT p FROM Publisher p WHERE p.archived IN :archived")
    List<Publisher> findAllPublisher(@Param("archived") List<Boolean> archived);
}
