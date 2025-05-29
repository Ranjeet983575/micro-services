package com.ms.shopnow.exceptions;

import com.ms.shopnow.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCategoryAlreadyExists(AlreadyExistsException ex) {
        ApiResponse<Object> build = ApiResponse.builder().message(ex.getMessage())
                .status("401").build();
        return ResponseEntity.ok(build);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCategoryAlreadyExists(NotFoundException ex) {
        ApiResponse<Object> build = ApiResponse.builder().message(ex.getMessage())
                .status("401").build();
        return ResponseEntity.ok(build);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponse<Object> build = ApiResponse.builder().message(errors.toString())
                .status("400").data(errors).build();
        return ResponseEntity.ok(build);


    }
}

