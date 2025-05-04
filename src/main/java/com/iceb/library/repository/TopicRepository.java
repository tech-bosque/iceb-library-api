package com.iceb.library.repository;

import com.iceb.library.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    @Query("SELECT t FROM Topic t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%')) AND t.archived IN :archived")
    List<Topic> findSimilarNames(@Param("name") String name, @Param("archived") List<Boolean> archived);

    @Query("SELECT t FROM Topic t WHERE t.archived IN :archived")
    List<Topic> findAllTopics(@Param("archived") List<Boolean> archived);
}
