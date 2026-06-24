package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCustomerPasswordException extends LibraryApiException {

    private final String detail;

    public InvalidCustomerPasswordException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();
        pb.setTitle("Invalid Customer Password");
        pb.setStatus(HttpStatus.BAD_REQUEST);
        pb.setDetail(detail);
        return pb;
    }
}
