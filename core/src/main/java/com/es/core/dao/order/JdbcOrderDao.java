package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public class JdbcOrderDao implements OrderDao{
    @Override
    public Optional<Order> get(Long orderId) {
        return Optional.empty();
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {

    }

    @Override
    public void saveOrderItems(List<OrderItem> orderItems) {

    }

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        return null;
    }
}
