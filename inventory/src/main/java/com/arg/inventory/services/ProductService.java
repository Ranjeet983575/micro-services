package com.arg.inventory.services;

import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Category;
import com.arg.inventory.entities.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(ProductDto product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product categoryDetails);
    void deleteProduct(Long id);
}
