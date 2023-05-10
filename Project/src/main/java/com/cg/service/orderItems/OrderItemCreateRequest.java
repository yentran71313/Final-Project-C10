package com.cg.service.orderItems;
import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemCreateRequest implements Validator {

    private Long id;

   private Long productId;

    private long quantity;

    private BigDecimal amount;



    @Override
    public boolean supports(Class<?> clazz) {
        return OrderItemCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderItemCreateRequest orderItemCreateRequest = (OrderItemCreateRequest) target;
//        String name = orderItemCreateRequest.getProductId().get;
//        Long categoryId = orderItemCreateRequest.getCategoryId();
//        Long brandId = orderItemCreateRequest.getBrandId();
//        String warranty = orderItemCreateRequest.getWarranty();
//        BigDecimal price = orderItemCreateRequest.getPrice();

//        if (name.length()==0 || name == ""){
//            errors.rejectValue("name","name.length","Name is not valid");
//        }
    }

    public OrderItem toOrderItem() {
        return new OrderItem()
                .setId(id)
                .setProductOrder(new Product().setId(productId))
                .setQuantity(quantity)
                ;
    }
}
