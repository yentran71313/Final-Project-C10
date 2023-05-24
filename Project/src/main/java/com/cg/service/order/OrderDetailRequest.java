package com.cg.service.order;


import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.customer.Customer;
import com.cg.service.orderItems.OrderItemCreateRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDetailRequest {
    private Long id;

    private BigDecimal totalAmount;

    private Customer customer;

    private List<OrderItemCreateRequest> orderItems;


}
