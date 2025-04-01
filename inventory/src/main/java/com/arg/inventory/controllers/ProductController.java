package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Product;
import com.arg.inventory.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {


    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveProduct(@Valid @RequestBody ProductDto productDto) {
        Product product = productService.saveProduct(productDto);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(product)
                .build();
        return ResponseEntity.ok(data);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(products)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(updatedProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data("Product deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
