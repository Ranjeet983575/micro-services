package com.arg.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductVariantDto {

    @NotNull(message = "Variant name cannot be null")
    @NotBlank(message = "Variant name cannot be blank")
    private String variantName;

    @NotNull(message = "SKU cannot be null")
    @NotBlank(message = "SKU cannot be blank")
    private String sku;

    @NotNull(message = "Price cannot be null")
    private Long price;

    @NotNull(message = "Product ID cannot be null")
    private Long productId;  // Reference to the parent Product
}
