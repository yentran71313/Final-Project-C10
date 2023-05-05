package com.cg.service.orderItems;

import lombok.Data;

@Data
public class OrderItemListRequest {
    private String search;
    private Long orderId;
    private Long productId;
}
