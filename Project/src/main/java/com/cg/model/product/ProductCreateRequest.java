package com.cg.model.product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateRequest productCreateRequest = (ProductCreateRequest) target;
        String name = productCreateRequest.getName();
        Long categoryId = productCreateRequest.getCategoryId();
        Long brandId = productCreateRequest.getBrandId();
        String warranty = productCreateRequest.getWarranty();
        BigDecimal price = productCreateRequest.getPrice();

        if (name.length()==0 || name == ""){
            errors.rejectValue("name","name.length","Name is not valid");
        }
    }

    public Product toProduct(Category category, Brand brand){
        return new Product()
                .setName(name)
                .setPrice(price)
                .setWarranty(warranty)
                .setCategory(category)
                .setBrand(brand);
    }
}
