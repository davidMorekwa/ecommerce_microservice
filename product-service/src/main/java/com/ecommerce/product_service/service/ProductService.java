package com.ecommerce.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.request.ProductRequest;
import com.ecommerce.product_service.request.StockUpdateRequest;
import com.ecommerce.product_service.util.Mappers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /*
     * Find Product by Name
     */
    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    /*
     * FInd Prodcut by Id
     */
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    /*
     * Get All products
     */
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    /*
     * Save product
     */
    public Product saveProduct(ProductRequest request){
        Product __product = Mappers.mapToProduct(request);
        return productRepository.save(__product);
    }

    /*
     * Delete product
     */
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    /*
     * Update stock quantity
     */
    public Product updateStockQuantity(StockUpdateRequest request){
        Optional<Product> _product = productRepository.findById(request.getProduct_id());
        if(!_product.isPresent()){
            return null;
        }
        _product.get().setIn_stock(_product.get().getIn_stock() - request.getQty());
        return productRepository.save(_product.get());
    }

    
}
