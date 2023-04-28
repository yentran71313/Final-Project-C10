package com.cg.api.order;

import com.cg.model.*;
import com.cg.service.order.OrderService;
import com.cg.service.orderItems.OrderItemCreateRequest;
import com.cg.service.orderItems.OrderItemDetailResponse;
import com.cg.service.orderItems.OrderItemService;
import com.cg.service.product.ProductListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping(value = "/api/user/order-items")
@AllArgsConstructor
public class OrderItemAPI {
    private final OrderItemService orderItemService;
    private final OrderService orderService;


    @GetMapping
//    public ResponseEntity<?> getOrderCurrent(){
    public ResponseEntity<?> getOrderItems(){
        Order order = new Order();

        // get current user;
        //get order with status Active, and orderItem of Order
        //parse order and orderItem to response add field total item = quantity* amount => return client;

        List<OrderItem> orderItems = orderService.findByOrder(order);

        if (orderItems.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderItemDetailResponse> orderItemDetailResponses = new ArrayList<>();

        for (OrderItem item : orderItems) {
            orderItemDetailResponses.add(item.toOrderItemDetailResponse());
        }

        return new ResponseEntity<>(orderItemDetailResponses, HttpStatus.OK);
    }

    @PatchMapping("/add/{orderItemId}")
    public ResponseEntity<?> addCartItem(@PathVariable long orderItemId) {
        //check product_id existed in orderItems = quantity +1;

        //product id , quantity
        //get Product => price add field amount, set field orderItems
        // get Order current=> orderItems
        //save orderItem

        OrderItem orderItem = orderItemService.findById(orderItemId).get();

        Product product = orderItem.getProductOrder();

        BigDecimal newPrice = product.getPrice();
        long currentQuantity = orderItem.getQuantity();
        long newQuantity = currentQuantity + 1;

        BigDecimal newAmount = newPrice.multiply(new BigDecimal(newQuantity));

        orderItem.getProductOrder().setPrice(newPrice);
        orderItem.setQuantity(newQuantity);
        orderItem.setAmount(newAmount);

        OrderItem newOrderItem = orderItemService.save(orderItem);

        Order order = orderItem.getOrder();

        BigDecimal bAmount = orderItemService.getSumAmount(order.getId());

        order.setTotalAmount(bAmount);

        orderService.save(order);

        long totalCartItemQuantity = orderItemService.countOrderItemByOrder(orderItem.getOrder());

        return new ResponseEntity<>(newOrderItem.toOrderItemDetailWithCountQuantityResponse(totalCartItemQuantity), HttpStatus.OK);
    }

    @PatchMapping("/minus/{OrderItemId}")
    public ResponseEntity<?> minusOrderItem(@PathVariable long OrderItemId) {

        OrderItem orderItem = orderItemService.findById(OrderItemId).get();

        Product product = orderItem.getProductOrder();

        BigDecimal newPrice = product.getPrice();
        long currentQuantity = orderItem.getQuantity();
        long newQuantity = currentQuantity - 1;

        BigDecimal newAmount = newPrice.multiply(new BigDecimal(newQuantity));

        orderItem.getProductOrder().setPrice(newPrice);
        orderItem.setQuantity(newQuantity);
        orderItem.setAmount(newAmount);

        OrderItem newOrderItem = orderItemService.save(orderItem);

        Order order = orderItem.getOrder();

        BigDecimal bAmount = orderItemService.getSumAmount(order.getId());

        order.setTotalAmount(bAmount);

        orderService.save(order);

        long totalOrderItemQuantity = orderItemService.countOrderItemByOrder(orderItem.getOrder());

        return new ResponseEntity<>(newOrderItem.toOrderItemDetailWithCountQuantityResponse(totalOrderItemQuantity), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{orderItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable long orderItemId) {

        orderItemService.remove(orderItemId);

        Order order = orderItemService.findById(orderItemId).get().getOrder();

        BigDecimal bAmount = orderItemService.getSumAmount(order.getId());

        order.setTotalAmount(bAmount);

        orderService.save(order);

        long totalOrderItemQuantity = orderItemService.countOrderItemByOrder(orderItemService.findById(orderItemId).get().getOrder());

        Map<String, Long> results = new HashMap<>();
        results.put("totalOrderItemQuantity", totalOrderItemQuantity);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/amount/{orderId}")
    public String getTotalAmountByOrderId(@PathVariable long orderId) {

        BigDecimal bAmount = orderItemService.getSumAmount(orderId);

        String amount = bAmount.toString();

        return amount;
    }

    @PostMapping
    public ResponseEntity<ProductListResponse> create(@RequestBody OrderItemCreateRequest orderItemCreateRequest, BindingResult bindingResult) throws IOException {

        orderItemService.create(orderItemCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
