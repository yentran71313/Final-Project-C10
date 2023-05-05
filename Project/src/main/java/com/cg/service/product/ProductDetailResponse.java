package com.cg.service.product;

import com.cg.model.Image;
import com.cg.model.Product;
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
    private List<String> urlImages;

    public ProductDetailResponse(Product product){
        id = product.getId();
        price = product.getPrice();
        name = product.getName();
        urlImages = product.getImages().stream().map(e -> e.getFileUrl() + "").collect(Collectors.toList());
    }
}
