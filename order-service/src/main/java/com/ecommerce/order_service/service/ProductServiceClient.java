package com.ecommerce.order_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.order_service.request.StockUpdateRequest;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestTemplate restTemplate;
    private String BASE_URL = "http://localhost:8082/api/v1/product";
    
    public void updateStockQuantity(Long product_id, Integer qty){
        String product_service_url = BASE_URL+"/stock/reduce";
        StockUpdateRequest request = new StockUpdateRequest(product_id, qty);
        restTemplate.put(product_service_url, request);
    }
}
