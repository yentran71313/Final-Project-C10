package com.cg.service.order;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.*;
import com.cg.model.auth.User;
import com.cg.model.auth.enums.StatusOrder;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import com.cg.model.product.Product;
import com.cg.model.product.ProductListResponse;
import com.cg.repository.CustomerRepository;
import com.cg.repository.LocationRegionRepository;
import com.cg.repository.OrderItemRepository;
import com.cg.repository.OrderRepository;

import com.cg.service.baseservice.IBaseService;
import com.cg.service.orderItems.OrderItemCreateRequest;
import com.cg.service.product.ProductService;
import com.cg.util.AppConstant;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService implements IBaseService<OrderListResponse, OrderListRequest, OrderCreateRequest, OrderDetailResponse> {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ProductService productService;
    private final CustomerRepository customerRepository;

    private final LocationRegionRepository locationRegionRepository;

    @Override
    public Page<OrderListResponse> getAllAndSearch(OrderListRequest request, Pageable pageable) {
        if (request.getSearch() != null) {
            request.setSearch("%" + request.getSearch() + "%");
        }
        Page<Order> orders = orderRepository.getAllAndSearch(request,pageable);
        List<OrderListResponse> orderListResponses = orders.stream().map(e->e.toOrderListResponse()).collect(Collectors.toList());
        Page<OrderListResponse> page = new PageImpl<>(orderListResponses,pageable,orders.getTotalElements());
        return page;
    }

    @Override
    public Optional<OrderDetailResponse> findById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Order"));
        }
        return Optional.of(new OrderDetailResponse(orderOptional.get()));
    }

    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public boolean checkout(OrderDetailRequest orderDetailRequest) {
        boolean success = false;
        try {
            Order order = new Order();
            order.setTotalAmount(orderDetailRequest.getTotalAmount());
            LocationRegion locationRegion = orderDetailRequest.getCustomer().getLocationRegion();
            locationRegion = locationRegionRepository.save(locationRegion);
            Customer customer = orderDetailRequest.getCustomer();
            customer.setLocationRegion(locationRegion);
            customer = customerRepository.save(customer);
            order.setCustomerOrder(customer);
            List<OrderItemCreateRequest> orderItems = orderDetailRequest.getOrderItems();
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (OrderItemCreateRequest item : orderItems) {
                Optional<Product> product = productService.findProductById(item.getProductId());
                OrderItem orderItem = item.toOrderItem();
                orderItem.setOrder(order);
                BigDecimal amount = product.get().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                orderItem.setAmount(amount);
                orderItemRepository.save(orderItem);

                totalAmount = totalAmount.add(amount);
            }
            BigDecimal DeliveryShipping = BigDecimal.valueOf(10);
            order.setTotalAmount(totalAmount.add(DeliveryShipping));
            order.setStatus(StatusOrder.PENDING);
            orderRepository.save(order);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void create(OrderCreateRequest orderCreateRequest) {
        checkExistDb(orderCreateRequest);
        Order order = orderRepository.save(orderCreateRequest.toOrder());
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void update(OrderCreateRequest orderCreateRequest) {
        checkExistDb(orderCreateRequest);
        Optional<Order> orderOptional = orderRepository.findById(orderCreateRequest.getId());
        if (!orderOptional.isPresent()) {
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Order"));
        }
        Order order = orderRepository.save(orderCreateRequest.toOrder());
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void delete(Long id) {

    }

    private void checkExistDb(OrderCreateRequest request) {
//        if(!customerRepository.existsById(request.getCustomerId())){
//            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Customer"));
//        };
//        if (!orderItemRepository.existsById(request.getOrderItems())) {
//            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Order Item"));
//        }
    }
}
