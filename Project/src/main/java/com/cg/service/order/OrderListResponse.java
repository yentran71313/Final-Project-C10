package com.cg.service.order;

import com.cg.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private Long id;

    private BigDecimal totalAmount;

    private String customerName;

    private String phone;

}
