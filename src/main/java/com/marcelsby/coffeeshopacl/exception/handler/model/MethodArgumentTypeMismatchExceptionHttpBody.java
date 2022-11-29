package com.marcelsby.coffeeshopacl.exception.handler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
public class MethodArgumentTypeMismatchExceptionHttpBody extends CommonExceptionHttpBody {

  @Getter
  @AllArgsConstructor
  private static class ArgumentTypeMismatchDetail {
    private String parameter;
    private String passedValue;
    private String expectedFormat;
  }

  private final ArgumentTypeMismatchDetail detail;

  public MethodArgumentTypeMismatchExceptionHttpBody(String message, HttpServletRequest request, HttpStatus status,
                                                     MethodArgumentTypeMismatchException exception) {
    super(message, request, status);

    String expectedType = Objects.isNull(exception.getRequiredType()) ? "Not specified" : exception.getRequiredType().getSimpleName();
    String passedValue = Objects.isNull(exception.getValue()) ? "null" : exception.getValue().toString();

    this.detail = new ArgumentTypeMismatchDetail(exception.getParameter().getParameterName(), passedValue, expectedType);
  }
}
