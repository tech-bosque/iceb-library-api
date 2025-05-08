package com.iceb.library.dto.borrow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowSearchDto {

    private UUID id;

    private UUID customerId;

    private String customerName;

    private String borrowDate;

}
