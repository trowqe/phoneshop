package com.es.core.model.cart;

import com.es.core.dao.phone.ItemNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Long, Long> items = new HashMap<>();
    
    public void addItem(Long itemId, Long quantity) {
        items.merge(itemId, quantity, Long::sum);
    }

    public void removeItem(Long itemId) throws ItemNotFoundException {
        items.remove(itemId);
    }

    public Map<Long, Long> getItems() {
        return items;
    }

}
