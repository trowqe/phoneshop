package com.es.core.service.cart;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    private Cart cart;

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        cart.addItem(phoneId, quantity);
    }

    @Override
    public void update(Map<Long, Long> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Long phoneId) {
        cart.removeItem(phoneId);
    }
}

