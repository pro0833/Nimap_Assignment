package com.example.nimap.controller;

import com.example.nimap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nimap.entity.Category;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size) {
        Page<Category> categories = categoryService.getAllCategories(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{di}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("di") Long id) throws Exception {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{di}")
    public ResponseEntity<Category> updateCategory(@PathVariable("di") Long id, @RequestBody Category categoryDetails) throws Exception {
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{di}")
    public ResponseEntity<?> deleteCategory(@PathVariable("di") Long id) throws Exception {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted...id = " + id);
    }
}
