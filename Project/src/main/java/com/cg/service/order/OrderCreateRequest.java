package com.cg.service.order;

import com.cg.model.*;
import com.cg.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest implements Validator {
    private Long id;

    private BigDecimal totalAmount;

    private Long customerId;

    private Long[] orderItems;

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderCreateRequest orderCreateRequest = (OrderCreateRequest) target;
//        String name = orderCreateRequest.getName();
//        Long categoryId = orderCreateRequest.getCategoryId();
//        Long brandId = orderCreateRequest.getBrandId();
//        String warranty = orderCreateRequest.getWarranty();
//        BigDecimal price = orderCreateRequest.getPrice();
//
//        if (name.length()==0 || name == ""){
//            errors.rejectValue("name","name.length","Name is not valid");
//        }
    }
    public Order toOrder(){
        return new Order()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setCustomerOrder(new Customer().setId(customerId))
                .setOrderItems(Arrays.stream(orderItems).map(e -> new OrderItem().setId(e)).collect(Collectors.toList()));
    }
}
