package com.cg.service.product;

import com.cg.model.Image;
import com.cg.model.product.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDetailResponse {

    private Long id;
    private String name;
    private BigDecimal price;

    private String description;

    private BigDecimal marketPrice;

    private String nameCategory;

    private String nameBrand;

    private String warranty;
    private List<String> urlImages;

    public ProductDetailResponse(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description= product.getDescription();
        marketPrice = product.getMarketPrice();
        nameCategory = product.getCategory().getName();
        nameBrand = product.getBrand().getName();
        warranty = product.getWarranty();
        urlImages = product.getImages().stream().map(e -> e.getFileUrl() + "").collect(Collectors.toList());
    }
}
