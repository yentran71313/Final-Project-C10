package com.cg.model;


import com.cg.service.product.ProductListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")

public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal marketPrice;

    private long priceUnit;

    private BigDecimal price;

    private String warranty;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "productOrder")
    private List<OrderItem> orderItems;

    public ProductListResponse toProductListResponse(Map images){
        return new ProductListResponse()
                .setName(name)
                .setPrice(price)
                .setWarranty(warranty)
                .setMarketPrice(marketPrice)
                .setNameBrand(brand.getName())
                .setNameCategory(category.getName())
                .setImages(images).setId(id);

    }



}
