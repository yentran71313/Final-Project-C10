package com.cg.service.category;


import com.cg.model.product.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListResponse {

    private Long id;
    private String name;

    public CategoryListResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
