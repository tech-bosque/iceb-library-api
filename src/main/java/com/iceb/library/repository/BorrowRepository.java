package com.iceb.library.repository;

import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    @Query("SELECT b FROM Borrow b " +
            "LEFT JOIN b.customer c " +
            "WHERE (:#{#params.customerId} IS NULL OR c.id = :#{#params.customerId}) " +
            "AND (:#{#params.customerName} IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :#{#params.customerName}, '%'))) " +
            "AND (:#{#params.borrowDate} IS NULL OR TO_CHAR(b.borrowDate, 'yyyy-MM-dd') = :#{#params.borrowDate})")
    List<Borrow> searchBorrows(@Param("params") BorrowSearchDto params);

}
