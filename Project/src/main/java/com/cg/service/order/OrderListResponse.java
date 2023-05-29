package com.cg.service.order;

import com.cg.model.OrderItem;
import com.cg.service.orderItems.OrderItemDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {

    private Long id;

    private BigDecimal totalAmount;

    private String customerName;

    private String phone;

    private String email;

    private String status;

    private String address;

    private String provinceName;

    private String provinceId;

    private String districtName;

    private String districtId;

    private String wardName;

    private String wardId;

    private List<OrderItemDetailResponse> orderItems;

}
