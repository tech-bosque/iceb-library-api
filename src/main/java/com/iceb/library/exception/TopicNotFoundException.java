package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TopicNotFoundException extends LibraryApiException{

    private final String detail;

    public TopicNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();
        pb.setTitle("Topic Not Created");
        pb.setStatus(HttpStatus.NOT_FOUND);
        pb.setDetail(detail);
        return pb;
    }
}
