package com.nisum.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    // Get all errors
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(x -> x.getDefaultMessage()).collect(Collectors.toList());
    System.err.println(errors);
    body.put("errors", errors);
    ExceptionResponse exc = new ExceptionResponse("Error en la validación del request.");
    if (!errors.isEmpty()) {
      exc = new ExceptionResponse(errors.get(0));
    }
    return new ResponseEntity<>(exc, headers, status);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionResponse> runtimeException(RuntimeException ex,
      WebRequest request) {
    ExceptionResponse exc = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionResponse> contraintException(ConstraintViolationException ex,
      WebRequest request) {
    String message = ex.getMessage();
    for (ConstraintViolation<?> iterable_element : ex.getConstraintViolations()) {
      message = iterable_element.getMessage();
    }
    ExceptionResponse exc = new ExceptionResponse(message);
    return new ResponseEntity<>(exc, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> exception(Exception ex, WebRequest request) {
    ExceptionResponse exc = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MessageException.class)
  public ResponseEntity<ExceptionResponse> exception(MessageException ex, WebRequest request) {
    ExceptionResponse exc = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exc, HttpStatus.BAD_REQUEST);
  }

}
