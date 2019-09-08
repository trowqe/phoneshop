package com.es.core.service.order;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.*;
import com.es.core.service.cart.CartService;
import com.es.core.service.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${delivery.price}")
    private BigDecimal deliveryPrice;

    @Autowired
    PhoneService phoneService;

    @Autowired
    CartService cartService;

    @Override
    public Order createOrder(Cart cart, DeliveryInfo deliveryInfo) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        cart.getItems().forEach((k, v) -> {
            OrderItem orderItem = createOrderItem(k, v, order);
            orderItems.add(orderItem);
        });
        order.setOrderItems(orderItems);
        order.setSubtotal(cartService.countTotalSum());
        order.setTotalPrice(cartService.countTotalSum().add(deliveryPrice));
        order.setDeliveryPrice(deliveryPrice);
        order.setContactPhoneNo(deliveryInfo.getContactPhoneNo());
        order.setDeliveryAddress(deliveryInfo.getDeliveryAddress());
        order.setFirstName(deliveryInfo.getFirstName());
        order.setLastName(deliveryInfo.getLastName());
        order.setStatus(OrderStatus.NEW);
        return order;
    }

    private OrderItem createOrderItem(Long phoneId, Long quantity, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setOrder(order);
        orderItem.setPhone(phoneService.get(phoneId));
        return orderItem;
    }

    @Override
    public void placeOrder(Order order) throws OutOfStockException {
        throw new UnsupportedOperationException("TODO");
    }
}
