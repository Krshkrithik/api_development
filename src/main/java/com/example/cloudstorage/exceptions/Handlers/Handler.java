package com.example.cloudstorage.exceptions.Handlers;

import com.example.cloudstorage.exceptions.GuarantorLimitExceed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class Handler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, e->e.getDefaultMessage()));
        log.warn(String.valueOf(errors));
        return ResponseEntity.status(404).body(errors);
    }

    @ExceptionHandler(GuarantorLimitExceed.class)
    public ResponseEntity<?> GuarantorLimitExceedExceptionHandler(GuarantorLimitExceed ex){
        Map<String,String> response = new HashMap<>();
        response.put("error",ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
