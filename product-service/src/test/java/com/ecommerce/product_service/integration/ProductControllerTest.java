package com.ecommerce.product_service.integration;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.ConfigTreePropertySource.Option;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecommerce.product_service.controller.ProductController;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.service.ProductService;

// @ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    List<Product> products = new ArrayList<Product>();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
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
     * Test the 'api/v1/products' endpoint
     */
    @Test
    public void getAllProductsRouteTest() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"));
    }
    /*
     * Test the result when no products are found
     */
    @Test
    public void getAllProductsRouteNoContentTest() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(new ArrayList<Product>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    /*
     * Test the 'api/v1/products/{name}' endpoint
     */
    @Test
    public void getProductByNameTest() throws Exception{
        Mockito.when(productService.findByName("Product 1")).thenReturn(Optional.of(products.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/name/{name}", "Product 1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100));
    }
    /*
     * Test the 'api/v1/products/{name}' endpoint when product not found
     */
    @Test
    public void getProductByNameNotFound() throws Exception{
        Mockito.when(productService.findByName("Product 3")).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/name/{name}", "Product 3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    /*
     * Test the /api/v1/products/{id} endpoint
     */
    @Test
    public void getProductById() throws Exception{
        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(products.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/id/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100));
    }
    /*
     * Test the /api/v1/products/{id} endpoint when product is not found
     */
    @Test
    public void getProductByIdNotFound() throws Exception{
        Mockito.when(productService.findById(3L)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/id/3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    /*
     * Test the POST /api/v1/products
     */
    
}
