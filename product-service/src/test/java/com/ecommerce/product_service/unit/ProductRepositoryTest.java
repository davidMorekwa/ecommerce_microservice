package com.ecommerce.product_service.unit;

import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    List<Product> products = new ArrayList<Product>();
    @Mock
    private ProductRepository productRepository;

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

    @Test
    public void getAllProductsTest(){
        when(productRepository.findAll()).thenReturn(products);
        List<Product> products = productRepository.findAll();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void getProductByNameTest(){
        when(productRepository.findByName("Product 1")).thenReturn(Optional.of(products.get(0)));
        Optional<Product> product = productRepository.findByName("Product 1");
        Assertions.assertThat(product.get().getName()).isEqualTo("Product 1");
    }

}
