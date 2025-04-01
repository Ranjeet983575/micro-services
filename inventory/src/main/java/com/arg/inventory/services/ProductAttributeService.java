package com.arg.inventory.services;

import com.arg.inventory.dto.ProductAttributeDto;
import com.arg.inventory.entities.ProductAttribute;

import java.util.List;

public interface ProductAttributeService {

    public ProductAttribute saveProductAttribute(ProductAttributeDto dto);

    public ProductAttribute updateProductAttribute(Long id, ProductAttributeDto dto);

    public ProductAttribute getProductAttribute(Long id);

    public List<ProductAttribute> getAllProductAttributes();
}
