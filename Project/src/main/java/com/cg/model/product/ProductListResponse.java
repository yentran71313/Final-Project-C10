package com.cg.model.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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
    private Long categoryId;
    private Long brandId;
    private String nameBrand;
    private String avatar;
    private Long avatarId;
    private String warranty;
    private List<String> images;
    private List<Long> imageIds;

    private String description;

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
