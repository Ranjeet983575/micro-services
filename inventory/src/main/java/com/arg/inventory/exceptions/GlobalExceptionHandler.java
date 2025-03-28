package com.arg.inventory.exceptions;

import com.arg.inventory.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleCategoryAlreadyExists(AlreadyExistsException ex) {
        ApiResponse<Object> build = ApiResponse.builder().message(ex.getMessage())
                .status("401").build();
        return ResponseEntity.ok(build);
    }
}

