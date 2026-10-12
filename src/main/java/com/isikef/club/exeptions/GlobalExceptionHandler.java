
package com.isikef.club.exeptions;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice
public class GlobalExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> handleConnectException(
            ConnectException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Server connection error: " + ex.getMessage());
    }
    
    @Override 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, 
            org.springframework.http.HttpHeaders headers, 
            HttpStatusCode status,
             WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
    @Override 
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex,
             org.springframework.http.HttpHeaders headers, 
             HttpStatusCode status, 
             WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getParameterValidationResults().forEach(result -> 
        result.getResolvableErrors().forEach(error ->
           errors.put(result.getMethodParameter().getParameterName(), error.getDefaultMessage())));
                return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    
  
}