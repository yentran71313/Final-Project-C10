package com.cg.model.product;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductListRequest {
    private String search;
    private Long brandId;
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
