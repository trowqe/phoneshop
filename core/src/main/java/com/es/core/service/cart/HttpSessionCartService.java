package com.es.core.service.cart;

import com.es.core.model.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpSessionCartService implements CartService {

    @Override
    public void initializeCart(Cart cart) {

    }
}

