package com.cg.service.order;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.*;
import com.cg.model.auth.User;
import com.cg.model.customer.Customer;
import com.cg.repository.OrderItemRepository;
import com.cg.repository.OrderRepository;
import com.cg.service.baseService.IBaseService;
import com.cg.service.orderItems.OrderItemCreateRequest;
import com.cg.util.AppConstant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderService implements IBaseService<OrderListResponse, OrderListRequest, OrderCreateRequest, OrderDetailResponse> {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;


//    private final CustomerRepository customerRepository;

    @Override
    public Page<OrderListResponse> getAllAndSearch(OrderListRequest request, Pageable pageable) {
        if (request.getSearch() != null) {
            request.setSearch("%" + request.getSearch() + "%");
        }
        return orderRepository.getAllAndSearch(request, pageable);
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
            Customer customer = orderDetailRequest.getCustomer();
            order.setCustomerOrder(orderDetailRequest.getCustomer());

            order = orderRepository.save(order);

            List<OrderItemCreateRequest> orderItems = orderDetailRequest.getOrderItems();

            for (OrderItemCreateRequest item : orderItems) {
                OrderItem orderItem = item.toOrderItem();
                orderItemRepository.save(orderItem.setOrder(order));
            }
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
