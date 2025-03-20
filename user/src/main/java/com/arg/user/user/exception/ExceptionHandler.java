package com.arg.user.user.exception;

import com.arg.user.user.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(ResourceNotFoundException ex) {
        ExceptionResponse resp = ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }


}
