package com.cg.service.orderItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemListResponse {

    private Long id;

    private Long productId;

    private long quantity;

    private BigDecimal amount;

    private Long orderId;

}
