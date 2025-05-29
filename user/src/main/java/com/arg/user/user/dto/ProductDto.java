package com.arg.user.user.dto;


import lombok.Data;

import java.util.List;


@Data
public class ProductDto {
    
    private String name;
    private String sku;
    private String description;
    private Long categoryId;
    private List<ProductVariantDto> productVariants;
}
