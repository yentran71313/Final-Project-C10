package com.cg.model.product;


import com.cg.model.BaseEntity;
import com.cg.model.Image;
import com.cg.model.OrderItem;
import com.cg.model.auth.enums.StatusOrder;
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
import java.util.ArrayList;
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
    @Column(columnDefinition = "longtext")
    private String description;

    private Long quantity;

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

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "productOrder")
    private List<OrderItem> orderItems;

    public ProductListResponse toProductListResponse(){
        List<Image> list = new ArrayList<>(images);
        if (quantity >0) {
            setStatus(ProductStatus.IN_STOCK);
        } else {
            setStatus(ProductStatus.OUT_STOCK);
        }
        return new ProductListResponse()
                .setId(id)
                .setStatus(status.getValue())
                .setName(name)
                .setPrice(price)
                .setBrandId(brand.getId())
                .setCategoryId(category.getId())
                .setWarranty(warranty)
                .setMarketPrice(marketPrice)
                .setNameBrand(brand.getName())
                .setAvatarId(avatar == null ? null : avatar.getId())
                .setImageIds(list.stream().map(Image::getId).collect(Collectors.toList()))
                .setNameCategory(category.getName())
                .setDescription(description)
                .setImages(list.stream().map(Image::getFileUrl).collect(Collectors.toList()))
                .setAvatar(avatar == null ? null : avatar.getFileUrl());




    }
}
