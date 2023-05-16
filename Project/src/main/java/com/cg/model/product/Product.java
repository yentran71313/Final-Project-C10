package com.cg.model.product;


import com.cg.model.BaseEntity;
import com.cg.model.Image;
import com.cg.model.OrderItem;
import com.cg.model.product.Brand;
import com.cg.model.product.Category;
import com.cg.model.product.ProductListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Image avatar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;


    @OneToMany(mappedBy = "productOrder")
    private List<OrderItem> orderItems;

    public ProductListResponse toProductListResponse(){
        List<Image> list = images.stream().collect(Collectors.toList());
        return new ProductListResponse()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setBrandId(brand.getId())
                .setCategoryId(category.getId())
                .setWarranty(warranty)
                .setMarketPrice(marketPrice)
                .setNameBrand(brand.getName())
                .setAvatarId(avatar.getId())
                .setImageIds(list.stream().map(Image::getId).collect(Collectors.toList()))
                .setNameCategory(category.getName())
                .setImages(list.stream().map(Image::getFileUrl).collect(Collectors.toList()))
                .setAvatar(avatar.getFileUrl());



    }
}
