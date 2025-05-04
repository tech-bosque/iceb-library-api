package com.iceb.library.repository;

import com.iceb.library.dto.CustomerSearchDto;
import com.iceb.library.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("""
        SELECT c FROM Customer c
        WHERE (
            :#{#params.search} IS NULL
            OR LOWER(c.name) LIKE LOWER(CONCAT('%', :#{#params.search}, '%'))
            OR LOWER(c.email) LIKE LOWER(CONCAT('%', :#{#params.search}, '%'))
            OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :#{#params.search}, '%'))
        )
        AND (:#{#params.rules} IS EMPTY OR c.rule IN :#(#params.rules)
        AND (:#{#params.archived} IS EMPTY OR c.archived IN :#{#params.archived})
    """)
    List<Customer> searchCustomers(@Param("params") CustomerSearchDto params);
}
