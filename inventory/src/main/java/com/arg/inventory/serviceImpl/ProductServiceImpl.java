package com.arg.inventory.serviceImpl;

import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Category;
import com.arg.inventory.entities.Product;
import com.arg.inventory.exceptions.NotFoundException;
import com.arg.inventory.repositories.ProductRepository;
import com.arg.inventory.services.CategoryService;
import com.arg.inventory.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private CategoryService categoryService;

    @Override
    public Product saveProduct(ProductDto dto) {
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(category);
        product.setDescription(dto.getDescription());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setCategory(productDetails.getCategory());
        product.setProductVariants(productDetails.getProductVariants());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }
}
