package com.es.core.dao.order;


import com.es.core.dao.phone.JdbcPhoneDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JdbcOrderDaoTest {


   @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcOrderDao jdbcOrderDao;

    @Test
    public void findAll(){
       assertTrue(jdbcOrderDao.findAll().isEmpty());
    }

    /*
    @Test
    public void save(){
        Order order = new Order();
        order.setFirstName("olga");
        order.setLastName("gritsatsueva");
        order.setDeliveryAddress("luban, Kypalovski 5-44");
        order.setContactPhoneNo("+375297803698");

        Phone  phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPhone(phone1);
        orderItem1.setOrder(order);

        Phone  phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPhone(phone2);
        orderItem2.setOrder(order);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);
        jdbcOrderDao.save(order);

        jdbcOrderDao.selectOrderItemsByOrderId(1L).forEach(e->{
            System.out.println(e.toString());});
        assertEquals(1, jdbcOrderDao.findAll().size());
    }

     */


}