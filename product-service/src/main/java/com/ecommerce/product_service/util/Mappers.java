package com.ecommerce.product_service.util;

import org.springframework.stereotype.Component;

import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.request.ProductRequest;
import com.ecommerce.product_service.request.CategoryRequest;

@Component
public class Mappers {
    /*
     * Util function to map ProductRequest class to Product model
     */
    public static Product mapToProduct(ProductRequest request){
        Product product = new Product();
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setIn_stock(request.getIn_stock());
        product.setPrice(request.getPrice());
        return product;
    }
    public static Category mapToCategory(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }
}
