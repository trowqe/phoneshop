package com.es.core.model.cart;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.model.cartItem.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Cart {

    Long userId = null;
    private Map<Long, CartItem> items;
    private BigDecimal totalSum;
    private Long totalQuantity;


    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean isInitialized() {
        return userId == null ? false : true;
    }

    public void initialize(Long userId) throws ItemNotFoundException {
        if (userId == null) {
            throw new ItemNotFoundException("Null customerId is not allowed");
        } else {
            this.userId = userId;
            this.totalQuantity=0L;
            this.totalSum=new BigDecimal(0.0);
        }
        items = new HashMap();
    }

    public void addItem(CartItem cartItem) {
        if (items.containsKey(cartItem)) {
            CartItem before = items.get(cartItem.getItemId());
            cartItem.setItemQuantity(before.getItemQuantity() + cartItem.getItemQuantity());
        } else {
            items.put(cartItem.getItemId(), cartItem);
        }
        totalSum.add(new BigDecimal(1.0));
        totalQuantity += cartItem.getItemQuantity();
    }

    public void removeItem(CartItem cartItem) throws ItemNotFoundException {
        if (items.containsKey(cartItem.getItemId())) {
            items.put(cartItem.getItemId(), cartItem);
        } else throw new ItemNotFoundException("cant delete not existing id");
    }

    public List<CartItem> getItems() {
        return new ArrayList(items.entrySet());
    }

    public void removeAll() {
        items = null;
    }
}
