package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.entities.Category;
import com.arg.inventory.services.CategoryService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<ApiResponse<Object>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(allCategories).build();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(category).build();
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(newCategory).build();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryService.updateCategory(id, categoryDetails);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(category).build();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(null).build();
        return ResponseEntity.ok(data);
    }


    public String rateLimiterFallback(Throwable t) {
        return "⚠\uFE0F Too many requests! Please try again later.";
    }

}
