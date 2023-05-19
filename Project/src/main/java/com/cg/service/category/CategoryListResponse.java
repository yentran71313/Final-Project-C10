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

    private String fileUrl;

    private Long imageId;

    public CategoryListResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.fileUrl = category.getImage().getFileUrl();
        this.imageId = category.getImage().getId();
    }
}
