package com.es.core.dao.cart;

import com.es.core.model.cart.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/context/applicationContext-coreTest.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CartTest {

    @Autowired
    private Cart cart;

    @Test
    public void addItem() {
        Long id1 = 0L;
        Long quantity1 = 3L;
        Long newQuantity1 = 8L;

        Map<Long, Long> cartAfterAdd = new HashMap<>();
        cartAfterAdd.put(id1, newQuantity1 + quantity1);

        cart.addItem(id1, quantity1);
        cart.addItem(id1, newQuantity1);
        assertEquals(cartAfterAdd, cart.getItems());
    }

}
