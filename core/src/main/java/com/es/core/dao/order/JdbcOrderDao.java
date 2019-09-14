package com.es.core.dao.order;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOrderDao implements OrderDao {

    private final String SQL_SELECT_ALL_ORDERS = "select * from orders ";

    private final String INSERT_INTO_ORDERS = "insert into orders (subtotal, deliveryPrice, totalPrice," +
            " firstName, lastName, deliveryAddress, contactPhoneNo, status) " +
            "  values (:subtotal, :deliveryPrice, :totalPrice, :firstName, :lastName, " +
            " :deliveryAddress, :contactPhoneNo, :status)";

    private final String INSERT_INTO_ORDER_ITEMS = "insert into orderitems (phoneId, quantity) values " +
            "(:phone.id, :quantity)";

    private final String INSERT_INTO_ORDER_2_ORDER_ITEM = "insert into order2orderitem (orderId, orderItemId) " +
            "values (:orderId, :orderItemId)";

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<Order> get(Long orderId) {
        return Optional.empty();
    }

    @Override
    public void save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(order);
        namedParameterJdbcTemplate.update(INSERT_INTO_ORDERS, namedParameters, keyHolder, new String[]{"id"});
        Long orderId = keyHolder.getKey().longValue();

        for (OrderItem orderItem : order.getOrderItems()) {
            KeyHolder keyHolder1 = new GeneratedKeyHolder();
            SqlParameterSource namedParameters1 = new BeanPropertySqlParameterSource(orderItem);
            namedParameterJdbcTemplate.update(INSERT_INTO_ORDER_ITEMS, namedParameters1, keyHolder1, new String[]{"id"});
            Long itemId = keyHolder1.getKey().longValue();
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("orderId", orderId);
            mapSqlParameterSource.addValue("orderItemId", itemId);
            namedParameterJdbcTemplate.update(INSERT_INTO_ORDER_2_ORDER_ITEM, mapSqlParameterSource);
        }

    }

public List<OrderItem> selectOrderItemsByOrderId(Long orderId){
        String SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID = "SELECT * FROM orderitems where id in " +
                "(select orderItemId from order2orderitem where orderId = :orderId)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("orderId", orderId);
       // return namedParameterJdbcTemplate.query(SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID, mapSqlParameterSource, );
    return null;
    }

    @Override
    public List<Order> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {

    }

}
