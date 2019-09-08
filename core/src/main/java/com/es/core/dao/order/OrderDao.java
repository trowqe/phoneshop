package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> get(Long orderId);
    void save(Order order);
    List<Order> findAll();
    void updateOrderStatus(Long orderId, OrderStatus orderStatus);
    void saveOrderItems(List<OrderItem> orderItems);
    List<OrderItem> getOrderItems(Long orderId);
}
