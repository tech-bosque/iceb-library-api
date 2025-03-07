package com.iceb.library.repository;

import com.iceb.library.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("SELECT u FROM Customer u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')) AND (:archived = true OR u.archived = :archived)")
    List<Customer> findSimilarNames(@Param("name") String name, @Param("archived") boolean archived);

    @Query("SELECT u FROM Customer u WHERE :archived = true OR u.archived = :archived")
    List<Customer> findAllCustomers(@Param("archived") boolean archived);
}
