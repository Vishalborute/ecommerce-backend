package com.example.vishal.ecommerce_backend.controller;

import com.example.vishal.ecommerce_backend.entity.Category;
import com.example.vishal.ecommerce_backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping()
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("getAll")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @PutMapping("{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return service.updateCategory(id, category);
    }

    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable Long id) {
        return service.deleteCategory(id);
    }

}
