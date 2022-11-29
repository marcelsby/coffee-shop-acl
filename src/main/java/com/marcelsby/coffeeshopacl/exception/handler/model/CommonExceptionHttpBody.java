package com.marcelsby.coffeeshopacl.exception.handler.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Getter
public class CommonExceptionHttpBody {

  private final LocalDateTime timestamp;
  private final Integer status;
  private final String error;
  private final String method;
  private final String path;
  private final String description;

  public CommonExceptionHttpBody(String message, HttpServletRequest request, HttpStatus status) {
    this.status = status.value();
    this.error = status.getReasonPhrase();
    this.method = request.getMethod();
    this.path = request.getRequestURI();
    this.description = message;
    this.timestamp = LocalDateTime.now();
  }
}
