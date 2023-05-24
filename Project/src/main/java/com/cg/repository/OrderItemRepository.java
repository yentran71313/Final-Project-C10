package com.cg.repository;

import com.cg.model.*;
import com.cg.service.orderItems.OrderItemCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

    long countOrderItemByOrder(Order order);

//    @Query("SELECT COUNT(c) FROM CartItem AS c WHERE c.cart.id = :cartId")
//    long countCartItemByCartId(@Param("cartId") long cartId);

    @Query(value = "SELECT SUM(oi.amount) FROM order_item AS oi WHERE oi.order_id = :orderId", nativeQuery = true)
    BigDecimal getSumAmount(@Param("orderId") long orderId);


}
