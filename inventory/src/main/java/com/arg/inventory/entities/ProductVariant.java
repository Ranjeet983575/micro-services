package com.arg.inventory.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String variantName;
    private String sku;
    private BigDecimal price;
    private Product product;
}
