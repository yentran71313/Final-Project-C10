package com.cg.service.product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest implements Validator {

    private String name;

    private Long categoryId;

    private Long brandId;

    private String warranty;

    private BigDecimal price;

    private MultipartFile multipartFile;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateRequest productCreateRequest = (ProductCreateRequest) target;
    }
}
