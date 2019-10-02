package com.es.core.dao.order;


import com.es.core.dao.phone.JdbcPhoneDao;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import com.es.core.model.phone.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JdbcOrderDaoTest {

    @Autowired
    private JdbcOrderDao jdbcOrderDao;

    @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void saveOrder() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(id1);


        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        order.setOrderItems(items);
        Long id = jdbcOrderDao.saveOrder(order);

        assertEquals(Long.valueOf(1), id);
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void saveOrderRollBack() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(10L);

        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        order.setOrderItems(items);
        Long id = jdbcOrderDao.saveOrder(order);

        assertEquals(Long.valueOf(1), id);
    }

    @Test
    public void getOrderItems() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(id1);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        jdbcTemplate.update(sql, id2, 5, 5);
        phone2.setId(id2);

        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(1L);
        orderItem2.setOrder(order);
        orderItem2.setPhone(phone2);
        items.add(orderItem2);

        order.setOrderItems(items);
        order.setStatus(OrderStatus.NEW);
        Long id = jdbcOrderDao.saveOrder(order);
        List<OrderItem> itemsFromBd = jdbcOrderDao.getOrderItems(id);

        assertEquals(2L, itemsFromBd.size());
    }

    @Test
    public void getOrderById() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(id1);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        jdbcTemplate.update(sql, id2, 5, 5);
        phone2.setId(id2);

        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(1L);
        orderItem2.setOrder(order);
        orderItem2.setPhone(phone2);
        items.add(orderItem2);

        order.setOrderItems(items);
        order.setStatus(OrderStatus.NEW);
        Long id = jdbcOrderDao.saveOrder(order);

        assertEquals(order.getId(), jdbcOrderDao.getOrder(id).get().getId());
    }

    @Test
    public void getOrderByNotExistingId() {
        assertEquals(Optional.empty(), jdbcOrderDao.getOrder(0L));
    }

    @Test
    public void getOrdersWhenNoOrdersInBD() {
        assertEquals(0, jdbcOrderDao.getOrders().size());
    }

    @Test
    public void getOrders() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(id1);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        jdbcTemplate.update(sql, id2, 5, 5);
        phone2.setId(id2);

        Order order1 = new Order();
        order1.setStatus(OrderStatus.NEW);

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order1);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(1L);
        orderItem2.setOrder(order1);
        orderItem2.setPhone(phone2);
        items.add(orderItem2);

        order1.setOrderItems(items);
        Long orderId1 = jdbcOrderDao.saveOrder(order1);

        Order order2 = new Order();
        order2.setStatus(OrderStatus.NEW);
        order2.setOrderItems(items);
        Long orderId2 = jdbcOrderDao.saveOrder(order2);

        assertEquals(2, jdbcOrderDao.getOrders().size());
    }

    @Test
    public void updateStatusWithId() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);
        phone1.setId(id1);

        Order order1 = new Order();

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1L);
        orderItem1.setOrder(order1);
        orderItem1.setPhone(phone1);
        items.add(orderItem1);

        order1.setOrderItems(items);
        order1.setStatus(OrderStatus.NEW);
        Long orderId1 = jdbcOrderDao.saveOrder(order1);

        jdbcOrderDao.updateStatusByOrderId(OrderStatus.DELIVERED, orderId1);

        assertEquals(jdbcOrderDao.getOrder(orderId1).get().getStatus(), OrderStatus.DELIVERED);
    }
}