package com.betrybe.agrix.ebytr.staff.exception.advice;

import org.springframework.http.HttpStatus;

/**
 * Classe para representar uma resposta de erro.
 */
public class ErrorResponse {
  private final HttpStatus status;
  private final String message;

  public ErrorResponse(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
