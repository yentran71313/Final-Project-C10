package com.cg.service.brand;

import com.cg.model.product.Brand;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandListResponse {

    private Long id;

    private String name;

    private String fileUrl;

    private Long imageId;

    public BrandListResponse(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.fileUrl = brand.getImage().getFileUrl();
        this.imageId=brand.getImage().getId();
    }

}
