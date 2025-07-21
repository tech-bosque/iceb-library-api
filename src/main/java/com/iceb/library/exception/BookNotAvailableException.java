package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BookNotAvailableException extends LibraryApiException{

    private final String detail;

    public BookNotAvailableException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();
        pb.setTitle("Book Not Available");
        pb.setStatus(HttpStatus.CONFLICT);
        pb.setDetail(detail);
        return pb;
    }
}
