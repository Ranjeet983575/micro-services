package com.arg.inventory.services;

import com.arg.inventory.dto.ProductDto;
import com.arg.inventory.entities.Product;

public interface ProductService {

    public Product saveProduct(ProductDto product);
}
