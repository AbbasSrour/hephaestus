package com.abbassrour.backend.exception.handlers;

import com.abbassrour.backend.BaseErrorCodes;
import com.abbassrour.backend.exception.APIException;
import com.abbassrour.backend.exception.ErrorResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class InvalidUUIDHandler {
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
    if (UUID.class.equals(e.getRequiredType())) {
      throw new APIException(BaseErrorCodes.INVALID_UUID);
    }

    throw e;
  }
}
