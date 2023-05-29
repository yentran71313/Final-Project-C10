package com.cg.model;


import com.cg.service.orderItems.OrderItemDetailResponse;
import com.cg.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productOrder;

    @Column(name = "quantity")
    private long quantity;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItemDetailResponse toOrderItemDetailResponse(){
        return new OrderItemDetailResponse()
                .setId(id)
                .setPrice(productOrder.getPrice())
                .setQuantity(quantity)
                .setAmount(amount)
                .setProductId(productOrder.getId())
                .setOrderId(order.getId());
    }

    public OrderItemDetailResponse toOrderItemDetailWithCountQuantityResponse(long totalOrderItemQuantity){
        return new OrderItemDetailResponse()
                .setId(id)
                .setQuantity(quantity)
                .setAmount(amount)
                .setProductId(productOrder.getId())
                .setOrderId(order.getId())
                .setTotalOrderItemQuantity(totalOrderItemQuantity);
    }
}
