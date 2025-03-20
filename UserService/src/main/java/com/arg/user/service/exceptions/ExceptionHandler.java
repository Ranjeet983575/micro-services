package com.arg.user.service.exceptions;

import com.arg.user.service.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse build = ApiResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .build();
        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }
}
