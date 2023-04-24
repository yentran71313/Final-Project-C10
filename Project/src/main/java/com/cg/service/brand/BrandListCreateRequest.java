package com.cg.service.brand;

import com.cg.model.Brand;
import com.cg.model.Image;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandListCreateRequest {
    @NotNull(message = "Name cannot be null")
    private String name;

    private Long id;

    private Long image;

    public Brand toBrand(){
        return new Brand()
                .setName(name)
                .setId(id)
                .setImage(new Image().setId(image));

    }
}
