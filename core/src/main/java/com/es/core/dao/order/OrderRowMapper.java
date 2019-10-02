package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("orders.id"));
        order.setSubtotal(resultSet.getBigDecimal("orders.subtotal"));
        order.setDeliveryPrice(resultSet.getBigDecimal("orders.deliveryPrice"));
        order.setTotalPrice(resultSet.getBigDecimal("orders.totalPrice"));
        order.setFirstName(resultSet.getString("orders.firstName"));
        order.setLastName(resultSet.getString("orders.lastName"));
        order.setDeliveryAddress(resultSet.getString("orders.deliveryAddress"));
        order.setContactPhoneNo(resultSet.getString("orders.contactPhoneNo"));
        order.setAdditionalInfo(resultSet.getString("orders.additionalInfo"));
        order.setStatus(OrderStatus.valueOf(resultSet.getString("orders.status")));
        return order;
    }
}
