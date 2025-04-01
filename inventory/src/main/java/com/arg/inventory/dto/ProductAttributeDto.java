package com.arg.inventory.dto;

import lombok.Data;

@Data
public class ProductAttributeDto {

    private String attributeName;
    private String attributeValue;
    private Long productId;
    
}
