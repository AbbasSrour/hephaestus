package com.abbassrour.backend.exception;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class APIException extends RuntimeException {
    private final ErrorCode errorCode;
    private final ErrorResponse errorResponse;

    public APIException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorResponse = new ErrorResponse(errorCode);
    }

    public ResponseEntity<ErrorResponse> response() {
        return errorResponse.response(this.errorCode.getStatus());
    }
}
