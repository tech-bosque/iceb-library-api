package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CustomerAlreadyExistsException extends LibraryApiException {

    private final String detail;

    public CustomerAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();
        pb.setTitle("Customer Already Exists");
        pb.setStatus(HttpStatus.CONFLICT);
        pb.setDetail(detail);
        return pb;
    }
}
