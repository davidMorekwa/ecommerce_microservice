package com.ecommerce.order_service.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long product_id;
    private Integer quantity;
}
