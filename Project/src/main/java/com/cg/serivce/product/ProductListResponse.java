package com.cg.serivce.product;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductListResponse {
    private Long id;
    private String name;
    private BigDecimal price;
}
