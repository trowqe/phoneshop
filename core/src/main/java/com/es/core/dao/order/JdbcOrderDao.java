package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JdbcOrderDao implements OrderDao {

    private static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE orders SET status = :status where id = :id";

    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";

    private static final String SELECT_ORDER_BY_ORDER_ID = "SELECT * FROM orders WHERE id = :id";

    private static final String SQL_SAVE_ORDER = "INSERT INTO orders (subtotal, deliveryPrice, totalPrice, " +
            "firstName, lastName, deliveryAddress, contactPhoneNo, additionalInfo, status) " +
            "VALUES (:subtotal, :deliveryPrice, :totalPrice, :firstName, " +
            ":lastName, :deliveryAddress, :contactPhoneNo, :additionalInfo, :status)";

    private static final String SQL_SAVE_ORDER_ITEMS = "INSERT INTO orderItems (orderId, phoneId, quantity) " +
            "VALUES (:orderId, :phoneId, :quantity)";

    private static final String SELECT_ITEMS = "SELECT * FROM orderItems INNER JOIN orders ON orderItems.orderId = :orderId " +
            "INNER JOIN phones ON phones.id = orderItems.phoneId";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Transactional
    @Override
    public Long saveOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(order);
        parameterSource.registerSqlType("status", Types.VARCHAR);

        namedParameterJdbcTemplate.update(SQL_SAVE_ORDER, parameterSource, keyHolder, new String[]{"id"});
        order.setId(keyHolder.getKey().longValue());
        saveOrderItems(order);
        return keyHolder.getKey().longValue();
    }

    private void saveOrderItems(Order order) {
        List<OrderItem> items = order.getOrderItems();
        List<MapSqlParameterSource> batchArgs = items.stream()
                .map(this::mapOrderItemProperties)
                .collect(Collectors.toList());
        namedParameterJdbcTemplate.batchUpdate(SQL_SAVE_ORDER_ITEMS, batchArgs.toArray(new MapSqlParameterSource[items.size()]));

    }

    public List<OrderItem> getOrderItems(Long orderId) {
        Map<String, Object> map = Collections.singletonMap("orderId", orderId);
        return namedParameterJdbcTemplate.query(SELECT_ITEMS, map,
                new OrderItemRowMapper());
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        Map<String, Object> map = Collections.singletonMap("id", id);

        try {
            Order order = namedParameterJdbcTemplate.queryForObject(SELECT_ORDER_BY_ORDER_ID, map, new OrderRowMapper());
            order.setOrderItems(getOrderItems(id));
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Order> getOrders() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_ORDERS, new OrderRowMapper());
    }

    @Override
    public void updateStatusByOrderId(OrderStatus status, Long orderId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.registerSqlType("status", Types.VARCHAR);
        map.addValue("id", orderId);
        map.addValue("status", status);
        namedParameterJdbcTemplate.update(UPDATE_ORDER_STATUS_BY_ID, map);
    }

    private MapSqlParameterSource mapOrderItemProperties(OrderItem orderItem) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("phoneId", orderItem.getPhone().getId());
        map.addValue("orderId", orderItem.getOrder().getId());
        map.addValue("quantity", orderItem.getQuantity());
        return map;
    }

}