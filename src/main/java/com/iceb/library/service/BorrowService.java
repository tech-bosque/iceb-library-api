package com.iceb.library.service;

import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.borrow.BorrowSearchDto;

import java.util.List;
import java.util.UUID;

public interface BorrowService {
    BorrowResponseDto createBorrow(BorrowRequestDto borrowRequestDto);
    BorrowResponseDto getBorrowById(UUID id);
    List<BorrowResponseDto> searchBorrows(BorrowSearchDto borrowSearchDto);
    BorrowResponseDto updateBorrowItem(UUID id, BorrowItemUpdateDto borrowItemUpdateDto);
    BorrowResponseDto deleteBorrow(UUID id);
    BorrowResponseDto borrowItemReturn(BorrowItemReturnDto borrowItemReturnDto);

}
