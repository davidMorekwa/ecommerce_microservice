package com.ecommerce.product_service.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.request.ProductRequest;
import com.ecommerce.product_service.service.ProductService;
import com.ecommerce.product_service.util.Mappers;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private List<Product> products = new ArrayList<>();

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp(){
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Description1");
        product.setName("Product 1");
        product.setPrice(100);
        product.setIn_stock(10);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(200);
        product2.setIn_stock(20);
        product2.setDescription("Description 2");
        products.add(product);
        products.add(product2);
    }

    /*
     * Test service class findAll method
     */
    @Test
    public void findAllProductsTest(){
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<Product> products = productService.findAll();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    /*
     * Test service class findByName method
     */
    @Test
    public void findProductByNameTest (){
        Mockito.when(productRepository.findByName("Product 2")).thenReturn(Optional.of(products.get(1)));
        Optional<Product> product = productService.findByName("Product 2");
        Assertions.assertThat(product.get().getName()).isEqualTo("Product 2");
        Assertions.assertThat(product.get().getId()).isEqualTo(2L);
    }

    /*
     * Test service class findById method
     */
    @Test
    public void findProductByIdTest(){
        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(products.get(1)));
        Optional<Product> product = productService.findById(2L);
        Assertions.assertThat(product.get().getId()).isEqualTo(2L);
    }
    /*
     * Test service class saveProduct method
     */
    @Test
    public void saveProductTest(){
        ProductRequest request = new ProductRequest();
        request.setName("Iphone 12");
        request.setDescription("THis is a description of the Iphone 12");
        request.setIn_stock(20);
        request.setPrice(1200.00);
        Product product = Mappers.mapToProduct(request);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.saveProduct(request);
        Assertions.assertThat(savedProduct.getName()).isEqualTo(product.getName());
    }

}
