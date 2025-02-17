package com.iceb.library.repository;

import com.iceb.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')) AND (:archived = true OR u.archived = :archived)")
    List<User> findSimilarNames(@Param("name") String name, @Param("archived") boolean archived);

    @Query("SELECT u FROM User u WHERE :archived = true OR u.archived = :archived")
    List<User> findAllUsers(@Param("archived") boolean archived);
}
