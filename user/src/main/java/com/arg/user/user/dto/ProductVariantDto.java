package com.arg.user.user.dto;


import lombok.Data;

@Data
public class ProductVariantDto {


    private String variantName;


    private String sku;

    private Long price;


    private Long productId;
}
