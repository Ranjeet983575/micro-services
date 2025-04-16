package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.dto.ProductVariantDto;
import com.arg.inventory.entities.ProductVariant;
import com.arg.inventory.services.ProductVariantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-variant")
@AllArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveProductVariant(@Valid @RequestBody ProductVariantDto dto) {
        ProductVariant variant = productVariantService.saveProductVariant(dto);
        ApiResponse<Object> response = ApiResponse.builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(variant)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariant>>> getAllProductVariants() {
        List<ProductVariant> variants = productVariantService.getAllProductVariants();
        ApiResponse<List<ProductVariant>> response = ApiResponse.<List<ProductVariant>>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(variants)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariant>> getProductVariantById(@PathVariable Long id) {
        ProductVariant variant = productVariantService.getProductVariantById(id);
        ApiResponse<ProductVariant> response = ApiResponse.<ProductVariant>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(variant)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariant>> updateProductVariant(
            @PathVariable Long id,
            @Valid @RequestBody ProductVariantDto dto) {

        ProductVariant updatedVariant = productVariantService.updateProductVariant(id, dto);
        ApiResponse<ProductVariant> response = ApiResponse.<ProductVariant>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(updatedVariant)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteProductVariant(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data("Product variant deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}

