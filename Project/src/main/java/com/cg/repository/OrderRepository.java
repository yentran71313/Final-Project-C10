package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.service.order.OrderListRequest;
import com.cg.service.order.OrderListResponse;
import com.cg.service.orderItems.OrderItemListRequest;
import com.cg.service.orderItems.OrderItemListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o " +
            "from Order  o " +
            "where (:#{#request.search} is null or o.customerOrder.fullName  like :#{#request.search})")
    Page<Order> getAllAndSearch(OrderListRequest request, Pageable pageable);
}
