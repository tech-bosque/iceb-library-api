package com.iceb.library.service.impl;

import com.iceb.library.dto.borrow.BorrowItemReturnDto;
import com.iceb.library.dto.borrow.BorrowItemUpdateDto;
import com.iceb.library.dto.borrow.BorrowRequestDto;
import com.iceb.library.dto.borrow.BorrowResponseDto;
import com.iceb.library.dto.borrow.BorrowSearchDto;
import com.iceb.library.entity.Borrow;
import com.iceb.library.entity.BorrowItem;
import com.iceb.library.exception.BorrowItemNotFoundException;
import com.iceb.library.exception.BorrowNotFoundException;
import com.iceb.library.repository.BorrowRepository;
import com.iceb.library.service.BookService;
import com.iceb.library.service.BorrowService;
import com.iceb.library.validation.BorrowValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.iceb.library.utils.TranslatorUtils;

@Service
public class BorrowServiceImpl implements BorrowService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowServiceImpl.class);

    @Autowired
    private BorrowValidator borrowValidator;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookService bookService;

    @Override
    public BorrowResponseDto createBorrow(BorrowRequestDto borrowRequestDto) {

        logger.info("Creating borrow");
        logger.debug("Creating book with details: {}", borrowRequestDto);

        Borrow borrow = Borrow.builder()
                .customer(borrowValidator.validateCustomerId(borrowRequestDto.getCustomerId()))
                .borrowDate(borrowRequestDto.getBorrowDate())
                .borrowItems(borrowRequestDto.getBorrowItems().stream().map(borrowItemRequestDto ->
                        borrowValidator.validateBorrowItem(borrowItemRequestDto)).toList())
                .build();

        logger.debug("Books changing availability to false");

        borrow.getBorrowItems().forEach(borrowItem -> bookService.updateBookAvailability(borrowItem.getBookId(), false));

        Borrow savedBorrow = borrowRepository.save(borrow);

        logger.debug("Borrow created with id: {}", savedBorrow.getId());
        logger.info("Borrow created");

        return TranslatorUtils.borrowToBorrowResponseDto(savedBorrow);
    }

    @Override
    public BorrowResponseDto getBorrowById(UUID id) {
        logger.info("Fetching borrow by ID");
        logger.debug("Fetching borrow with ID: {}", id);

        Borrow borrow = findBorrowById(id);
        BorrowResponseDto responseDto = TranslatorUtils.borrowToBorrowResponseDto(borrow);

        logger.debug("Fetched borrow: {}", responseDto);
        logger.info("Fetched borrow by ID successfully");
        return responseDto;
    }

    @Override
    public List<BorrowResponseDto> searchBorrows(BorrowSearchDto borrowSearchDto) {
        logger.info("Fetching borrows with filters");
        logger.debug("Fetching borrows with filters: {}", borrowSearchDto);

        List<Borrow> borrows = borrowRepository.searchBorrows(borrowSearchDto);

        List<BorrowResponseDto> responseDtos = borrows.stream()
                .map(TranslatorUtils::borrowToBorrowResponseDto)
                .collect(Collectors.toList());

        logger.debug("Fetched borrows: {}", responseDtos);
        logger.info("Fetched borrows with filters successfully");
        return responseDtos;
    }

    @Override
    public BorrowResponseDto updateBorrowItem(UUID id, BorrowItemUpdateDto borrowItemUpdateDto) {
        logger.info("Updating borrow item");
        logger.debug("Updating borrow item with borrow ID: {} and borrow items details: {}", id, borrowItemUpdateDto);

        Borrow existingBorrow = findBorrowById(id);

        BorrowItem existingBorrowItem =  existingBorrow.getBorrowItems().stream()
                .filter(borrowItemSearch -> borrowItemSearch.getId().equals(borrowItemUpdateDto.getBorrowItemId()))
                .findFirst()
                .orElseThrow(() -> new BorrowItemNotFoundException("The borrow item with the provided ID does not exist in the given borrow"));

        existingBorrowItem.setBookId(borrowItemUpdateDto.getBookId());
        existingBorrowItem.setActualReturnDate(borrowItemUpdateDto.getActualReturnDate());

        Borrow updatedBorrow = borrowRepository.save(existingBorrow);

        logger.debug("Borrow Item updated with Borrow ID: {}", updatedBorrow.getId());
        logger.info("Borrow updated successfully");

        return TranslatorUtils.borrowToBorrowResponseDto(updatedBorrow);
    }

    @Override
    public BorrowResponseDto borrowItemReturn(BorrowItemReturnDto borrowItemReturnDto){

        logger.info("Returning borrow item");
        logger.debug("Returning borrow item with details: {}", borrowItemReturnDto);

        Borrow borrow = findBorrowById(borrowItemReturnDto.getBorrowId());

        BorrowItem borrowItem =  borrow.getBorrowItems().stream()
                .filter(borrowItemSearch -> borrowItemSearch.getId().equals(borrowItemReturnDto.getBorrowItemId()))
                .findFirst()
                .orElseThrow(() -> new BorrowItemNotFoundException("The borrow item with the provided ID does not exist in the given borrow"));

        borrowItem.setActualReturnDate(borrowItemReturnDto.getActualReturnDate());

        logger.debug("Book changing availability to true");
        bookService.updateBookAvailability(borrowItem.getBookId(), true);

        borrowRepository.save(borrow);
        logger.debug("Borrow item returned successfully");

        return TranslatorUtils.borrowToBorrowResponseDto(borrow);
    }

    @Override
    public BorrowResponseDto deleteBorrow(UUID id) {
        //TODO validate if we are going to allow Borrow deletions
        return null;
    }

    private Borrow findBorrowById(UUID id) {
        logger.info("Finding borrow by ID");
        logger.debug("Finding borrow with ID: {}", id);

        return borrowRepository.findById(id)
                .orElseThrow(() -> new BorrowNotFoundException("The borrow with the provided ID does not exist"));
    }

}
