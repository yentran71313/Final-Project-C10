package com.cg.model.product;

import com.cg.model.Image;
import lombok.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


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

    public ProductListResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.marketPrice = product.getMarketPrice();
        this.nameCategory = product.getCategory().getName();
        this.nameBrand = product.getBrand().getName();
        this.warranty = product.getWarranty();
        this.images = product.getImages().stream().collect(Collectors.toMap(Image::getId,image -> image.getFileUrl()));
    }

}
