package com.abbassrour.backend.user.exception;

// Write me java string enum

import com.abbassrour.backend.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCodes implements ErrorCode {
  NOT_FOUND("error.user.notFound", HttpStatus.NOT_FOUND, "User not found"),
  INVALID_EMAIL("error.user.invalidEmail", HttpStatus.UNPROCESSABLE_ENTITY, "Invalid email"),
  INVALID_USERNAME(
      "error.user.invalidUsername", HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username"),
  INVALID_PASSWORD(
      "error.user.invalidPassword", HttpStatus.UNPROCESSABLE_ENTITY, "Invalid password"),
  UNKNOWN_ERROR("error.user.unknown", HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");

  public final String code;
  public final HttpStatus status;
  public final String message;
}
