package com.iceb.library.dto.borrow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowItemReturnDto {

    private UUID borrowId;

    private UUID borrowItemId;

    private LocalDate actualReturnDate;

}
