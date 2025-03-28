package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Product;
import com.arg.inventory.services.CategoryService;
import com.arg.inventory.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final CategoryService categoryService;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveProduct(@Valid @RequestBody ProductDto productDto) {
        //Product product = productService.saveProduct(productDto);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(null).build();
        return ResponseEntity.ok(data);
    }

}
