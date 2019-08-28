package com.es.core.model.cart;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.model.cartItem.CartItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Cart {

    private Map<Long, Long> items = new HashMap<>();

    private BigDecimal totalSum;
    private Long totalItems;

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public void addItem(Long itemId, Long quantity) {
        if (items.containsKey(itemId)) {
          Long quantityBefore = items.get(itemId);
          items.put(itemId, quantity + quantityBefore);
        } else {
            items.put(itemId, quantity);
        }

    }

    public void removeItem(Long itemId) throws ItemNotFoundException {
        if (items.containsKey(itemId)) {
            items.remove(itemId);
        } else throw new ItemNotFoundException("cant delete not existing id");
    }

    public Map<Long, Long> getItems() {
        return items;
    }
}
