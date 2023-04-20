package com.cg.service.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal marketPrice;
    private String nameCategory;
    private String nameBrand;
    private String warranty;
    private Map<Long,String> images;

    public ProductListResponse(Long id, String name, BigDecimal price, BigDecimal marketPrice, String nameCategory, String nameBrand, String warranty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.marketPrice = marketPrice;
        this.nameCategory = nameCategory;
        this.nameBrand = nameBrand;
        this.warranty = warranty;
    }

}
