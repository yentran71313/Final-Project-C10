package com.cg.service.category;



import com.cg.model.Category;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryListCreateRequest implements Validator {

    private String name;

    private Long id;

    public Category toCategory(){
        return new Category()
                .setId(id)
                .setName(name);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryListCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryListCreateRequest categoryListCreateRequest =(CategoryListCreateRequest) target;
        String name = categoryListCreateRequest.getName();
        if (name.length()==0 || name == ""){
            errors.rejectValue("name","name.length","Name is not valid");
        }
    }
}
