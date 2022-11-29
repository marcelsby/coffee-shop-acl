package com.marcelsby.coffeeshopacl.exception.handler;

import com.marcelsby.coffeeshopacl.exception.DomainException;
import com.marcelsby.coffeeshopacl.exception.EmptySearchResultException;
import com.marcelsby.coffeeshopacl.exception.RecordNotFoundException;
import com.marcelsby.coffeeshopacl.exception.handler.model.CommonExceptionHttpBody;
import com.marcelsby.coffeeshopacl.exception.handler.model.MethodArgumentTypeMismatchExceptionHttpBody;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestControllerAdvice
public class GlobalApiExceptionHandler {

  private final MessageSource messageSource;

  public GlobalApiExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(EmptySearchResultException.class)
  public ResponseEntity<Void> handleEmptySearchResult() {
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<CommonExceptionHttpBody> handleRecordNotFound(RecordNotFoundException exception, HttpServletRequest request) {
    String exceptionMessage = messageSource
            .getMessage(exception.getMessage(), exception.getArgs(), Locale.getDefault());

    return new ResponseEntity<>(new CommonExceptionHttpBody(exceptionMessage, request, HttpStatus.NOT_FOUND),
            HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<CommonExceptionHttpBody> handleDomainException(DomainException exception, HttpServletRequest request) {
    String exceptionMessage = messageSource.getMessage(exception.getMessage(), null, Locale.getDefault());

    return ResponseEntity.badRequest()
            .body(new CommonExceptionHttpBody(exceptionMessage, request, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<MethodArgumentTypeMismatchExceptionHttpBody> handleMethodArgumentTypeMismatch(
          MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
    String exceptionMessage = messageSource.getMessage("Exception.methodArgumentTypeMismatch", null, Locale.getDefault());

    return ResponseEntity.badRequest().body(
            new MethodArgumentTypeMismatchExceptionHttpBody(exceptionMessage, request, HttpStatus.BAD_REQUEST, exception));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<CommonExceptionHttpBody> handleUnexpectedException(Exception exception, HttpServletRequest request) {
    String exceptionMessage = messageSource.getMessage("Exception.unexpected", null, Locale.getDefault());

    exception.printStackTrace();

    return ResponseEntity.internalServerError()
            .body(new CommonExceptionHttpBody(exceptionMessage, request, HttpStatus.BAD_REQUEST));
  }
}
