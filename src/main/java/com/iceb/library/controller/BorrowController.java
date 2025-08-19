package com.iceb.library.controller;

import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/borrow")
@Tag(name = "Borrow Management", description = "APIs for managing Borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    @Operation(summary = "Create a new borrow")
    public ResponseEntity<BorrowResponseDto> createBorrow(@Valid @RequestBody BorrowRequestDto borrowRequestDto) {
        BorrowResponseDto createdBorrow = borrowService.createBorrow(borrowRequestDto);
        return ResponseEntity.ok(createdBorrow);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a borrow by ID")
    public ResponseEntity<BorrowResponseDto> getBorrowById(@PathVariable UUID id) {
        BorrowResponseDto borrow = borrowService.getBorrowById(id);
        return ResponseEntity.ok(borrow);
    }

    @PostMapping("/search")
    @Operation(summary = "Get all borrows or search with filters")
    public ResponseEntity<List<BorrowResponseDto>> searchBorrows(@RequestBody @Valid BorrowSearchDto borrowSearchDto) {
        List<BorrowResponseDto> borrows = borrowService.searchBorrows(borrowSearchDto);
        return ResponseEntity.ok(borrows);
    }

    @PutMapping("/update-borrow-item/{id}")
    @Operation(summary = "Update a borrow")
    public ResponseEntity<BorrowResponseDto> updateBorrowItem(@PathVariable UUID id, @Valid @RequestBody BorrowItemUpdateDto borrowRequestDto) {
        BorrowResponseDto updatedBorrow = borrowService.updateBorrowItem(id, borrowRequestDto);
        return ResponseEntity.ok(updatedBorrow);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a borrow")
    public ResponseEntity<BorrowResponseDto> deleteBorrow(@PathVariable UUID id) {
        BorrowResponseDto archivedBorrow = borrowService.deleteBorrow(id);
        return ResponseEntity.ok(archivedBorrow);
    }

    @PostMapping("/return")
    @Operation(summary = "Return a borrow item")
    public ResponseEntity<BorrowResponseDto> borrowItemReturn(@RequestBody @Valid BorrowItemReturnDto borrowItemReturnDto) {
        BorrowResponseDto updatedBorrow = borrowService.borrowItemReturn(borrowItemReturnDto);
        return ResponseEntity.ok(updatedBorrow);
    }
}
