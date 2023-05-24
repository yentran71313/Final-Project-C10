package com.cg.model.product;

import com.cg.model.Image;
import com.cg.model.product.Brand;
import com.cg.model.product.Category;
import com.cg.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
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

    private String description;

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
        if(price == null || price.compareTo(BigDecimal.ZERO) <=0 ){
            errors.rejectValue("price", "price.value", "price must not be null");
        }

        if(categoryId <= 0){
            errors.rejectValue("categoryId", "height.value", "Category is not exist");
        }
        if(name == null || name.isEmpty()){
            errors.rejectValue("name", "description.value", "Name must not be empty");
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
                .setAvatar(new Image().setId(avatarId))
                .setQuantity(0L)
                .setDescription(description)
                .setStatus(ProductStatus.OUT_STOCK)
                .setImages(Arrays.stream(images).map(e -> new Image().setId(e)).collect(Collectors.toSet()));
    }
}
