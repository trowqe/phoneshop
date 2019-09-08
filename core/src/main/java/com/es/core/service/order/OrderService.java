package com.es.core.service.order;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.DeliveryInfo;
import com.es.core.model.order.Order;
import com.es.core.model.order.OutOfStockException;

public interface OrderService {
    Order createOrder(Cart cart, DeliveryInfo deliveryInfo);
    void placeOrder(Order order) throws OutOfStockException;
}
