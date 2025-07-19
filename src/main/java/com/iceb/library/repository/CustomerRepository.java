package com.iceb.library.repository;

import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("SELECT c FROM Customer c " +
            "WHERE (:#{#params.name} IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :#{#params.name}, '%'))) " +
            "AND (:#{#params.email} IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :#{#params.email}, '%'))) " +
            "AND (:#{#params.phone} IS NULL OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :#{#params.phone}, '%'))) " +
            "AND (:#{#params.archived} IS NULL OR :#{#params.archived} = TRUE OR c.archived = :#{#params.archived})")
    List<Customer> searchCustomers(@Param("params") CustomerSearchDto params);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.email) = LOWER(:email)")
    Optional<Customer> findByEmail(@Param("email") String email);
}
