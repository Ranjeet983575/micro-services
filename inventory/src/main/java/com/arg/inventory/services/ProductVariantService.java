package com.arg.inventory.services;

import com.arg.inventory.dto.ProductVariantDto;
import com.arg.inventory.entities.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    ProductVariant saveProductVariant(ProductVariantDto dto);
    List<ProductVariant> getAllProductVariants();
    ProductVariant getProductVariantById(Long id);
    ProductVariant updateProductVariant(Long id, ProductVariantDto productVariantDetails);
    void deleteProductVariant(Long id);
}
