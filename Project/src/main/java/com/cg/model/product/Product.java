package com.cg.model.product;


import com.cg.model.BaseEntity;
import com.cg.model.CartItem;
import com.cg.model.Image;
import com.cg.model.OrderItem;
import com.cg.model.product.Brand;
import com.cg.model.product.Category;
import com.cg.model.product.ProductListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    private List<Image> images;

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
