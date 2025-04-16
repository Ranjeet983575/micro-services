package com.arg.inventory.serviceImpl;

import com.arg.inventory.dto.ProductVariantDto;
import com.arg.inventory.entities.Product;
import com.arg.inventory.entities.ProductVariant;
import com.arg.inventory.exceptions.NotFoundException;
import com.arg.inventory.repositories.ProductVariantRepository;
import com.arg.inventory.services.ProductService;
import com.arg.inventory.services.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;

    @Override
    public ProductVariant saveProductVariant(ProductVariantDto dto) {
        Product product = productService.getProductById(dto.getProductId());

        ProductVariant variant = new ProductVariant();
        variant.setVariantName(dto.getVariantName());
        variant.setSku(dto.getSku());
        variant.setPrice(dto.getPrice());
        variant.setProduct(product);

        return productVariantRepository.save(variant);
    }

    @Override
    public List<ProductVariant> getAllProductVariants() {
        return productVariantRepository.findAll();
    }

    @Override
    public ProductVariant getProductVariantById(Long id) {
        return productVariantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProductVariant not found with id: " + id));
    }

    @Override
    public ProductVariant updateProductVariant(Long id, ProductVariantDto dto) {
        ProductVariant variant = getProductVariantById(id);
        Product product = productService.getProductById(dto.getProductId());

        variant.setVariantName(dto.getVariantName());
        variant.setSku(dto.getSku());
        variant.setPrice(dto.getPrice());
        variant.setProduct(product);

        return productVariantRepository.save(variant);
    }

    @Override
    public void deleteProductVariant(Long id) {
        productVariantRepository.deleteById(id);
    }
}
