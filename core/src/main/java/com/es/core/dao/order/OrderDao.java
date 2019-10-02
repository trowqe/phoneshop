package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Long saveOrder(Order order);
    Optional<Order> getOrder(Long id);
    List<Order> getOrders();
    void updateStatusByOrderId(OrderStatus status, Long orderId);
}
