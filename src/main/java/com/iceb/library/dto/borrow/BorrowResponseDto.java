package com.iceb.library.dto.borrow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowResponseDto {

    private UUID id;
    private UUID customerId;
    private LocalDate borrowDate;
    private List<BorrowItemResponseDto> borrowItems;
}
