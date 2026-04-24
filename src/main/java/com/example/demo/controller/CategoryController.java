package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.CategoryService;
import com.example.demo.entity.Category;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor Injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        System.out.println(category);
        return categoryService.save(category);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "Category deleted successfully";
    }
}