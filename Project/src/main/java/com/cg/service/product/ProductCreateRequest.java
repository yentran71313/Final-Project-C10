package com.cg.service.product;
import com.cg.model.Brand;
import com.cg.model.Category;
import com.cg.model.Image;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest implements Validator {

    private Long id;
    private String name;

    private Long categoryId;

    private Long brandId;

    private String warranty;

    private BigDecimal price;

    private Long avatarId;

    private Long[] images;

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

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setWarranty(warranty)
                .setCategory(new Category().setId(categoryId))
                .setBrand(new Brand().setId(brandId))
                .setImages(Arrays.stream(images).map(e -> new Image().setId(e)).collect(Collectors.toSet()));
    }
}
