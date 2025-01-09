package com.ecommerce.product_service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.product_service.controller.ProductController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ProductController productController;

	/*
	 * Test if the context loads
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(productController).isNotNull();
	}
	/*
	 * Test HTTP request response
	 */
	@Test
	void testWelcomeRoute() throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/api/v1/products/welcome", String.class)).contains("Welcome to the product service");	
	}
	
}
