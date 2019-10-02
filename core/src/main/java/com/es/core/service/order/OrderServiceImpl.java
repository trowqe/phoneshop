package com.es.core.service.order;

import com.es.core.dao.order.OrderDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import com.es.core.model.order.OutOfStockException;
import com.es.core.service.cart.CartService;
import com.es.core.service.phone.PhoneService;
import com.es.core.service.stock.StockService;
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
    private PhoneService phoneService;

    @Autowired
    private CartService cartService;

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order getOrderByOrderId(Long orderId) {
       return orderDao.getOrder(orderId).orElse(null);
    }

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setOrderItems(createOrderItemsFromCart(cart, order));
        order.setSubtotal(cartService.countTotalSum());
        order.setTotalPrice(cartService.countTotalSum().add(deliveryPrice));
        order.setDeliveryPrice(deliveryPrice);
        order.setStatus(OrderStatus.NEW);
        return order;
    }

    private List<OrderItem> createOrderItemsFromCart(Cart cart, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        cart.getItems().forEach((k, v) -> {
            OrderItem orderItem = createOrderItem(k, v, order);
            try {
                stockService.checkStock(orderItem);
            } catch (OutOfStockException e) {
                orderItem.setQuantity(0L);
            }
            orderItems.add(orderItem);
        });
        return orderItems;
    }

    private OrderItem createOrderItem(Long phoneId, Long quantity, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setOrder(order);
        orderItem.setPhone(phoneService.get(phoneId));
        return orderItem;
    }

    @Override
    public Long placeOrder(Order order) throws OutOfStockException {

        order.getOrderItems()
                .forEach(i->stockService.checkStock(i));
        return orderDao.saveOrder(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.getOrders();
    }

    @Override
    public void updateStatusById(OrderStatus status, Long orderId) {
        orderDao.updateStatusByOrderId(status, orderId);
    }
}
