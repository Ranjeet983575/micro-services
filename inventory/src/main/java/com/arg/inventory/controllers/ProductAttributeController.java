package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.dto.ProductAttributeDto;
import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Product;
import com.arg.inventory.entities.ProductAttribute;
import com.arg.inventory.services.ProductAttributeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-attribute")
@AllArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveProductAttribute(@Valid @RequestBody ProductAttributeDto productDto) {
        ProductAttribute product = productAttributeService.saveProductAttribute(productDto);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS)
                .status("200")
                .data(product)
                .build();
        return ResponseEntity.ok(data);
    }
}
