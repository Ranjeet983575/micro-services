package com.arg.inventory.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private String name;
    private String sku;
    private String description;
    private Long categoryId;
}
