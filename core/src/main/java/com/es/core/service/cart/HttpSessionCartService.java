package com.es.core.service.cart;

import com.es.core.dao.phone.PhoneNotFoundException;
import com.es.core.model.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    Cart cart;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        Map<Long, Long> beforeAdd = cart.getItems();
        beforeAdd.put(phoneId, quantity);
        System.out.println(beforeAdd);
        cart.setItems(beforeAdd);
    }

    @Override
    public void update(Map<Long, Long> items) {
        Map<Long, Long> beforeUpdate = cart.getItems();
        //TODO
    }

    @Override
    public void remove(Long phoneId) {
        if (cart.getItems().containsKey(phoneId)) {
            cart.getItems().remove(phoneId);
        } else {
            throw new PhoneNotFoundException();
        }
    }


}
