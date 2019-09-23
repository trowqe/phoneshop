package com.es.phoneshop.web.controller.cart;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


public class CartItemForm {

    @Valid
    private Map<Long, CartItem> cartItems;

    public CartItemForm() {
        cartItems = new HashMap<>();
    }

    public Map<Long, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Long, CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
