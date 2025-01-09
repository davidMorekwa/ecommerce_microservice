package com.ecommerce.order_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.request.OrderRequest;
import com.ecommerce.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> __order_list = orderService.getAllOrders();
        if(__order_list.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(__order_list);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        Optional<Order> __order = orderService.getOrderById(id);
        if(__order.isPresent()){
            return ResponseEntity.ok().body(__order.get());
        } else {
            return ResponseEntity.badRequest().body("Order Not found");
        }
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getOrderByProductId(@PathVariable("product_id") Long id) {
        List<Order> __order_list = orderService.getOrderByProductId(id);
        if(__order_list.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(__order_list);
        }
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order __order = orderService.addOrder(request);
            URI location = URI.create(String.format("/api/v1/order/%s", __order.getId()));
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred when trying to create the order");
        }
    }
    
    
    
    

}
