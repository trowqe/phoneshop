package com.es.core.service;

import com.es.core.dao.phone.JdbcPhoneDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import com.es.core.service.cart.HttpSessionCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
public class HttpSessionCartServiceTest {

    @Autowired
    @InjectMocks
    private HttpSessionCartService cartService;

    @Mock
    private Cart cart;

    @Mock
    private JdbcPhoneDao phoneDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPhone() {
        Long id = 0L;
        Long quantity = 3L;
        Map<Long, Long> itemsInCart = new HashMap<>();
        itemsInCart.put(id, quantity);
        when(cart.getItems()).thenReturn(itemsInCart);
        assertEquals(quantity, cartService.countTotalItem());
    }

    @Test
    public void update() {
        Long id1 = 0L;
        Long quantity1 = 3L;
        Long newQuantity1 = 8L;

        Long id2 = 4L;
        Long quantity2 = 3L;
        Long newQuantity2 = 2L;

        Map<Long, Long> itemsInCart = new HashMap<>();
        itemsInCart.put(id1, quantity1);
        itemsInCart.put(id2, quantity2);

        Map<Long, Long> updatedItemsInCart = new HashMap<>();
        updatedItemsInCart.put(id1, newQuantity1);
        updatedItemsInCart.put(id2, newQuantity2);

        when(cart.getItems()).thenReturn(itemsInCart);
        cartService.update(updatedItemsInCart);
        assertEquals(Long.valueOf(10L), cartService.countTotalItem());
    }

    @Test
    public void remove() {
        Long id1 = 0L;
        Long quantity1 = 1L;

        Long id2 = 4L;
        Long quantity2 = 1L;

        Map<Long, Long> itemsInCart = new HashMap<>();
        itemsInCart.put(id1, quantity1);
        itemsInCart.put(id2, quantity2);

        when(cart.getItems()).thenReturn(itemsInCart);
        itemsInCart.remove(id2);
        assertEquals(Long.valueOf(1L), cartService.countTotalItem());
    }

    @Test
    public void countTotalItem() {
        Long id1 = 0L;
        Long quantity1 = 1L;

        Long id2 = 4L;
        Long quantity2 = 5L;

        Map<Long, Long> itemsInCart = new HashMap<>();
        itemsInCart.put(id1, quantity1);
        itemsInCart.put(id2, quantity2);

        when(cart.getItems()).thenReturn(itemsInCart);

        assertEquals(Long.valueOf(6), cartService.countTotalItem());
    }

    @Test
    public void countTotalSum() {
        Long id1 = 0L;
        Long quantity1 = 1L;
        Long id2 = 4L;
        Long quantity2 = 1L;
        Map<Long, Long> itemsInCart = new HashMap<>();
        itemsInCart.put(id1, quantity1);
        itemsInCart.put(id2, quantity2);

        Phone phone = new Phone();
        phone.setBrand("motorola");
        phone.setModel("x style");
        phone.setPrice(BigDecimal.valueOf(10));

        when(phoneDao.get(any(Long.class))).thenReturn(Optional.of(phone));
        //  Mockito.doReturn(price1).when(phoneDao).get(any( Long.class)).get().getPrice();

        when(cart.getItems()).thenReturn(itemsInCart);
        assertEquals(BigDecimal.valueOf(20), cartService.countTotalSum());
    }

}