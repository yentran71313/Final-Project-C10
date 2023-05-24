package com.cg.api.order;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.auth.enums.StatusOrder;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import com.cg.model.product.Product;
import com.cg.model.product.ProductCreateRequest;
import com.cg.service.order.OrderCreateRequest;
import com.cg.service.order.OrderDetailRequest;
import com.cg.service.order.OrderListRequest;
import com.cg.service.order.OrderService;
import com.cg.service.orderItems.OrderItemCreateRequest;
import com.cg.service.orderItems.OrderItemService;
import com.cg.service.product.ProductService;
import com.cg.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user/orders")
@AllArgsConstructor
public class OrderAPI {
    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final ProductService productService;

    private final AppUtils appUtil;


    @GetMapping
    public ResponseEntity<?> getAllOrder(OrderListRequest request, Pageable pageable) {
        return new ResponseEntity<>(orderService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody OrderDetailRequest orderDetailRequest) {

        try {
            boolean success = orderService.checkout(orderDetailRequest);

            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

            throw new DataInputException("Contact the system administrator (MS001)");

        } catch (Exception e) {
            throw new DataInputException("Contact the system administrator (MS002)");
        }
    }

    @PostMapping("/update/{idOrder}")
    public ResponseEntity<?> update(@PathVariable Long idOrder,@RequestBody OrderCreateRequest OrderCreateRequest) {
        try {
            OrderCreateRequest.setId(idOrder);
            orderService.update(OrderCreateRequest);
        } catch (Exception e) {
            throw new DataInputException("Contact the system administrator (MS002)");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/updateStatus/{idOrder}")
    public ResponseEntity<?> updateStatus(@PathVariable Long idOrder, @RequestParam String status) throws IOException {
        orderService.updateStatus(idOrder,status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
