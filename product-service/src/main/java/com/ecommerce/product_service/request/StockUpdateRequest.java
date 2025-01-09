package com.ecommerce.product_service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateRequest {
    private Long product_id;
    private Integer qty;
}
