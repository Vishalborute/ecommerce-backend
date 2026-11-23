package com.example.vishal.ecommerce_backend.service;

import com.example.vishal.ecommerce_backend.entity.Category;
import com.example.vishal.ecommerce_backend.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    private Category findCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,  // Status: 404 Not Found
                                "Category not found"
                        )
                );
    }

    public Category addCategory(Category category) {

        if (repository.existsByName(category.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "category name already exists"
            );
        }
        return repository.save(category);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return findCategoryById(id);
    }

    public Category updateCategory(Long id, Category category) {
        Category updatedCategory = findCategoryById(id);
        updatedCategory.setName(category.getName());
        return repository.save(updatedCategory);
    }

    public String deleteCategory(Long id) {
        Category category = findCategoryById(id);
        repository.delete(category);
        return "Category deleted";
    }
}


