package com.arg.inventory.services;

import com.arg.inventory.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, Category categoryDetails);

    void deleteCategory(Long id);
}
