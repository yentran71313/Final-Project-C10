package com.cg.service.brand;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandListCreateRequest {
    @NotNull(message = "Name cannot be null")
    private String name;


}
