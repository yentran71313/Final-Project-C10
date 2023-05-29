package com.cg.model;


import com.cg.model.auth.Role;
import com.cg.model.auth.enums.StatusOrder;
import com.cg.model.customer.Customer;
import com.cg.model.product.ProductStatus;
import com.cg.service.order.OrderListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerOrder;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    public OrderListResponse toOrderListResponse() {
        return new OrderListResponse()
                .setId(id)
                .setCustomerName(customerOrder.getFullName())
                .setStatus(status.getValue())
                .setPhone(customerOrder.getPhoneNumber())
                .setEmail(customerOrder.getEmail())
                .setAddress(customerOrder.getLocationRegion().getAddress())
                .setProvinceName(customerOrder.getLocationRegion().getProvinceName())
                .setProvinceId(customerOrder.getLocationRegion().getProvinceId())
                .setDistrictName(customerOrder.getLocationRegion().getDistrictName())
                .setDistrictId(customerOrder.getLocationRegion().getDistrictId())
                .setWardName(customerOrder.getLocationRegion().getWardName())
                .setWardId(customerOrder.getLocationRegion().getWardId())
                .setOrderItems(orderItems.stream().map(OrderItem::toOrderItemDetailResponse).collect(Collectors.toList()))
                .setTotalAmount(totalAmount);

    }
}
