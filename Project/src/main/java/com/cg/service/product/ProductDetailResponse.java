package com.cg.service.product;

import com.cg.model.Image;
import com.cg.model.product.Product;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDetailResponse {

    private Long id;
    private String name;
    private List<String> urlImages;

    public ProductDetailResponse(Product product){
        id = product.getId();
        name = product.getName();
        urlImages = product.getImages().stream().map(e -> e.getId() + "").collect(Collectors.toList());
    }
}
