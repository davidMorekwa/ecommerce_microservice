package com.ecommerce.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.repository.CategoryRepository;
import com.ecommerce.product_service.request.CategoryRequest;
import com.ecommerce.product_service.util.Mappers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /*
     * Create Category
     */
    public Category saveCategory(CategoryRequest request){
        Category __category = Mappers.mapToCategory(request);
        return categoryRepository.save(__category);
    }
    /*
     * Read all categories
     */
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    /*
     * Read by id
     */
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    /*
     * Read by Name
     */
    public Optional<Category> getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }
    /*
     * Delete Category
     */
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
