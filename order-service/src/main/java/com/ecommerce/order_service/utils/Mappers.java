package com.ecommerce.order_service.utils;

import java.sql.Timestamp;
import java.util.Date;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.request.OrderRequest;

public class Mappers {
    public static Order maptToOrder(OrderRequest request){
        Order order = new Order();
        order.setProduct_id(request.getProduct_id());
        order.setQuantity(request.getQuantity());
        order.setUser_id(1L);
        order.setCreated_at(new Timestamp(new Date().getTime()));
        order.setUpdated_at(new Timestamp(new Date().getTime()));
        return order;
    }
}
