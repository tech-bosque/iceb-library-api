package com.iceb.library.repository;

import com.iceb.library.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    @Query("SELECT t FROM Topic t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%')) AND (:archived = true OR t.archived = :archived)")
    List<Topic> findSimilarNames(@Param("name") String name, @Param("archived") boolean archived);

    @Query("SELECT t FROM Topic t WHERE :archived = true OR t.archived = :archived")
    List<Topic> findAllTopics(@Param("archived") boolean archived);
}
