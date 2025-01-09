package com.ecommerce.product_service.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.request.ProductRequest;
import com.ecommerce.product_service.request.StockUpdateRequest;
import com.ecommerce.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    
    @GetMapping("/welcome")
    public @ResponseBody String welcome(){
        return "Welcome to the product service";
    }
    /*
     * Return all products
     */
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Product> products = productService.findAll();
        log.info("Request to get all products");
        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(products);
            
        }
    }
    /*
     * Get product by name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name){
        Optional<Product> product = productService.findByName(name);
        if(product.isPresent()){
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /*
     * Get product by id
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id: '"+id.toString(0)+"' not found");
        }
    }

    /*
     * Create a new product
     */
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest request){
        try {
            Product savedProduct = productService.saveProduct(request);
            URI location = URI.create(String.format("/api/v1/products/id/%s", savedProduct.getId()));
            return ResponseEntity.created(location).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the product: " + e.getMessage());
        }
    }
    /*
     * Delete Product
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product successfully deleted");
    }

    /*
     * Update stock quantity
     */
    @PutMapping("/stock/reduce")
    public ResponseEntity<?> putMethodName(@RequestBody StockUpdateRequest request) {
        try {
            Product _product = productService.updateStockQuantity(request);
            URI location = URI.create(String.format("/api/v1/products/id/%s", _product.getId()));
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error occurred when trying to update stock information");
        }
        
    }

}
