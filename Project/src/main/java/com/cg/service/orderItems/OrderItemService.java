package com.cg.service.orderItems;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.*;
import com.cg.repository.OrderItemRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.baseservice.IBaseService;
import com.cg.util.AppConstant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderItemService implements IBaseService<OrderItemListResponse, OrderItemListRequest, OrderItemCreateRequest, OrderItem> {

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    @Override
    public Page<OrderItemListResponse> getAllAndSearch(OrderItemListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return null;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (!orderItemOptional.isPresent()){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Order Item"));
        }
        return orderItemOptional;
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public BigDecimal getSumAmount(long orderId) {
        return orderItemRepository.getSumAmount(orderId);
    }

    public long countOrderItemByOrder(Order order) {
        return orderItemRepository.countOrderItemByOrder(order);
    }

    @Override
    public void create(OrderItemCreateRequest orderItemCreateRequest) {
        checkExistDb(orderItemCreateRequest);
        orderItemRepository.save(orderItemCreateRequest.toOrderItem());
    }

    @Override
    public void update(OrderItemCreateRequest orderItemCreateRequest) {
        checkExistDb(orderItemCreateRequest);
        Optional<OrderItem> productOptional = orderItemRepository.findById(orderItemCreateRequest.getId());
        if (!productOptional.isPresent()){
            throw  new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
        orderItemRepository.save(orderItemCreateRequest.toOrderItem());
    }

    @Override
    public void delete(Long id) {

    }

    public void remove(Long id) {
        orderItemRepository.deleteById(id);
    }

    private void checkExistDb(OrderItemCreateRequest request){
        if(!productRepository.existsById(request.getProductId())){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
    }
}
