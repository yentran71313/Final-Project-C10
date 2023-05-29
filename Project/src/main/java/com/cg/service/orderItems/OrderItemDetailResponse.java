package com.cg.service.orderItems;

import com.cg.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailResponse {
    private Long id;

    private long quantity;

    private BigDecimal amount;

    private BigDecimal price;

    private Long productId;

    private Long orderId;

    private long totalOrderItemQuantity;


    public OrderItemDetailResponse(OrderItem orderItem) {
        id=orderItem.getId();
        productId=orderItem.getProductOrder().getId();
        quantity=orderItem.getQuantity();
        amount=orderItem.getAmount();
        orderId=orderItem.getOrder().getId();
    }
}
