package com.abbassrour.backend;

import com.abbassrour.backend.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseErrorCodes implements ErrorCode {
  INVALID_UUID("error.base.invalidUUID", "Invalid UUID", HttpStatus.BAD_REQUEST),
  UNKNOWN_ERROR("error.base.unknown", "Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String code;
  private final String message;
  private final HttpStatus status;

  BaseErrorCodes(String code, String message, HttpStatus status) {
    this.code = code;
    this.message = message;
    this.status = status;
  }
}
