package com.ecommerce.product_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.request.CategoryRequest;
import com.ecommerce.product_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> __category_list = categoryService.getAllCategories();
        if(__category_list.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(__category_list);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable("id") Long id) {
        Optional<Category> __category = categoryService.getCategoryById(id);
        if(__category.isPresent()){
            return ResponseEntity.ok().body(__category);
        } else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getMethodName(@PathVariable("name") String name) {
        Optional<Category> __category = categoryService.getCategoryByName(name);
        if (__category.isPresent()) {
            return ResponseEntity.ok().body(__category);
        } else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody CategoryRequest request) {
        try {
            Category __saved_category = categoryService.saveCategory(request);
            URI location = URI.create(String.format("/api/v1/category/id/%s", __saved_category.getId()));
            return ResponseEntity.created(location).body("Product saved");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred when trying to save category");
        }
    }
    
    
    
    
}
