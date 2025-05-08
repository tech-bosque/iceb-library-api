package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BorrowNotFoundException extends LibraryApiException{

    private final String detail;

    public BorrowNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();
        pb.setTitle("Borrow Not Found");
        pb.setStatus(HttpStatus.NOT_FOUND);
        pb.setDetail(detail);
        return pb;
    }
}
