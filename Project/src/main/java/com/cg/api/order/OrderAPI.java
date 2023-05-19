package com.cg.api.order;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.auth.enums.StatusOrder;
import com.cg.model.product.Product;
import com.cg.service.order.OrderDetailRequest;
import com.cg.service.order.OrderListRequest;
import com.cg.service.order.OrderService;
import com.cg.service.orderItems.OrderItemCreateRequest;
import com.cg.service.orderItems.OrderItemService;
import com.cg.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user/orders")
@AllArgsConstructor
public class OrderAPI {
    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllOrder(OrderListRequest request, Pageable pageable){
        return new ResponseEntity<>(orderService.getAllAndSearch(request,pageable),HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderItemCreateRequest orderItemCreateRequest) {


            Long productId = orderItemCreateRequest.getProductId();

            Product product = productService.findProductById(productId).get();


            Order order = new Order();
            order.setStatus(StatusOrder.PENDING);

            order.setTotalAmount(product.getPrice());

            Order newOrder = orderService.save(order);

            order = newOrder;

            OrderItem orderItem = new OrderItem();
            orderItem.setId(0L);
            orderItem.setOrder(newOrder);
            orderItem.setProductOrder(product);
            orderItem.getProductOrder().setName(product.getName());
            orderItem.getProductOrder().setPrice(product.getPrice());
            orderItem.setQuantity(1L);
            orderItem.setAmount(product.getPrice());
            orderItemService.save(orderItem);

        long countQuantity = orderItemService.countOrderItemByOrder(order);
        Map<String, Long> results = new HashMap<>();
        results.put("totalOrderItemQuantity", countQuantity);

        return new ResponseEntity<>(results, HttpStatus.CREATED);

    }


    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody OrderDetailRequest orderDetailRequest) {

        try {
            boolean success = orderService.checkout(orderDetailRequest);

            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

            throw new DataInputException("Liên hệ với quản trị hệ thống (MS001)");

        } catch (Exception e) {
            throw new DataInputException("Liên hệ với quản trị hệ thống (MS002)");
        }
    }


}
