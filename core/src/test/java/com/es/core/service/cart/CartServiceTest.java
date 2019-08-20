package com.es.core.service.cart;


import com.es.core.dao.phone.PhoneNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    public void addPhone() {
        cartService.addPhone(1L, 2L);
        assertEquals(1, cartService.getCart().getItems().size());
        cartService.addPhone(1L, 1L);
        assertEquals(1, cartService.getCart().getItems().size());
        cartService.addPhone(2L, 1L);
        assertEquals(2, cartService.getCart().getItems().size());
    }

    @Test
    public void remove() {
        cartService.addPhone(1L, 1L);
        cartService.addPhone(2L, 1L);
        cartService.addPhone(3L, 1L);
        cartService.remove(2L);
        assertEquals(2, cartService.getCart().getItems().size());
    }

    @Test(expected = PhoneNotFoundException.class)
    public void removeNotExistingPhoneId() {
        cartService.remove(0L);
    }

    @Test
    public void update() {
        Map<Long, Long> items = new HashMap<>();
        items.put(1L, 1L);
        items.put(2L, 2L);

        cartService.addPhone(2L, 2L);
        cartService.addPhone(3L, 3L);

        cartService.update(items);

        //assertEquals(items, cartService.getCart().getItems());
    }
}
