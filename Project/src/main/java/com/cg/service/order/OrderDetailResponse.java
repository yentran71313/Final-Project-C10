package com.cg.service.order;

import com.cg.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDetailResponse {
    private Long id;

    private BigDecimal totalAmount;

    private Long customerId;

    private List<String> orderItems;

    public OrderDetailResponse(Order order){
        id = order.getId();
        totalAmount = order.getTotalAmount();
        customerId = order.getCustomerOrder().getId();
        orderItems = order.getOrderItems().stream().map(e -> e.getId() + "").collect(Collectors.toList());
    }
}
