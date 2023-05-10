package com.cg.model.product;

import lombok.Data;


@Data
public class ProductListRequest {
    private String search;
    private Long brandId;
    private Long categoryId;
}