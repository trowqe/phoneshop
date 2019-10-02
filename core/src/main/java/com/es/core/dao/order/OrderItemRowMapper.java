package com.es.core.dao.order;


import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Phone;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    private BeanPropertyRowMapper<Phone> phoneMapper = new BeanPropertyRowMapper<>(Phone.class);
    private OrderRowMapper orderRowMapper = new OrderRowMapper();

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderItem orderItem = new OrderItem();
        Phone phone = phoneMapper.mapRow(resultSet, resultSet.getRow());
        phone.setId(resultSet.getLong("phones.id"));
        orderItem.setPhone(phone);
        orderItem.setQuantity(resultSet.getLong("quantity"));
        Order order = orderRowMapper.mapRow(resultSet, i);
        orderItem.setOrder(order);
        return orderItem;
    }
}
