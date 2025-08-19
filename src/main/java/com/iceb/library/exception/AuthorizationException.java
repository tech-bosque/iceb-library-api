package com.iceb.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AuthorizationException extends LibraryApiException {

    private final String detail;

    public AuthorizationException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
      var pb = super.toProblemDetail();
      pb.setTitle("Authorization Failed");
      pb.setStatus(HttpStatus.FORBIDDEN);
      pb.setDetail(detail);
      return pb;
    }
}
