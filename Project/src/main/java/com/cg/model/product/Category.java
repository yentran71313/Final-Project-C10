package com.cg.model.product;


import com.cg.model.BaseEntity;
import com.cg.model.Image;
import com.cg.service.category.CategoryListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public CategoryListResponse toCategoryListResponse (){
        return new CategoryListResponse()
                .setId(id)
                .setName(name)
                .setFileUrl(image.getFileUrl());
    }
}
