package com.abbassrour.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {
  private final String code;
  private final String message;

  public ErrorResponse(ErrorCode err) {
    this.code = err.getCode();
    this.message = err.getMessage();
  }

  public ResponseEntity<ErrorResponse> response(HttpStatus status) {
    return ResponseEntity.status(status).body(this);
  }
}
