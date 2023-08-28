package com.abbassrour.backend.exception.handlers;

import com.abbassrour.backend.exception.APIException;
import com.abbassrour.backend.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(APIException.class)
  public ResponseEntity<ErrorResponse> handleAPIException(APIException exception) {
    return exception.response();
  }
}
