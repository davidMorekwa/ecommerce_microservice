package com.ecommerce.order_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.request.OrderRequest;
import com.ecommerce.order_service.request.StockUpdateRequest;
import com.ecommerce.order_service.respository.OrderRepository;
import com.ecommerce.order_service.utils.Mappers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;

    /*
     * Create Order
     */
    public Order addOrder(OrderRequest request){
        Order __order = Mappers.maptToOrder(request);
        Order saved_order = orderRepository.save(__order);
        productServiceClient.updateStockQuantity(__order.getProduct_id(), __order.getQuantity());
        return saved_order;
    }
    /*
     * Get order by ID
     */
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }
    /*
     * Get orders by products ID
     */
    public List<Order> getOrderByProductId(Long product_id){
        return orderRepository.findByProduct_id(product_id);
    }
    /*
     * Get all orders
     */
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
    

}
