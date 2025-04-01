package com.arg.inventory.serviceImpl;

import com.arg.inventory.dto.ProductAttributeDto;
import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Product;
import com.arg.inventory.entities.ProductAttribute;
import com.arg.inventory.repositories.ProductAttributeRepository;
import com.arg.inventory.services.ProductAttributeService;
import com.arg.inventory.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;

    private final ProductService productService;


    @Override
    public ProductAttribute saveProductAttribute(ProductAttributeDto dto) {
        Product productById = productService.getProductById(dto.getProductId());
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setProduct(productById);
        productAttribute.setAttributeName(dto.getAttributeName());
        productAttribute.setAttributeValue(dto.getAttributeValue());
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public ProductAttribute updateProductAttribute(Long id, ProductAttributeDto dto) {
        return null;
    }

    @Override
    public ProductAttribute getProductAttribute(Long id) {
        return null;
    }

    @Override
    public List<ProductAttribute> getAllProductAttributes() {
        return List.of();
    }
}
