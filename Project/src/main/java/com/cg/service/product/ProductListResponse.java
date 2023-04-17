package com.cg.service.product;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductListResponse {
    private Long id;
    private String name;
    private BigDecimal price;
}
