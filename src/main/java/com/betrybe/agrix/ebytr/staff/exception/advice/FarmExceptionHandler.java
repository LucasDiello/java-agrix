package com.betrybe.agrix.ebytr.staff.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para lidar com exceções relacionadas a fazendas.
 */
@ControllerAdvice
public class FarmExceptionHandler {

  /**
   * Manipulador para a exceção NotFoundException.
   *
   * @param error a exceção NotFoundException lançada
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException error) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
        error.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
