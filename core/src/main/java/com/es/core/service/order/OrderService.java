package com.es.core.service.order;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import com.es.core.model.order.OutOfStockException;

import java.util.List;

public interface OrderService {
    Order getOrderByOrderId(Long orderId);
    Order createOrder(Cart cart);
    Long placeOrder(Order order) throws OutOfStockException;
    List<Order> findAll();
    void updateStatusWithId(OrderStatus status, Long orderId);
}
