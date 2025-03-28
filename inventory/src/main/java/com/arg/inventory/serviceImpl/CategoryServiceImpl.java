package com.arg.inventory.serviceImpl;

import com.arg.inventory.entities.Category;
import com.arg.inventory.exceptions.AlreadyExistsException;
import com.arg.inventory.repositories.CategoryRepository;
import com.arg.inventory.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new AlreadyExistsException("Category Not Found with id " + id));

    }

    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new AlreadyExistsException("Category with name '" + category.getName() + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category category1 = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

