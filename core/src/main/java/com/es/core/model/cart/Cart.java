package com.es.core.model.cart;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    private Map<Long, Long> items = new HashMap<>();

    public Map<Long, Long> getItems() {
        return items;
    }

    public void setItems(Map<Long, Long> items) {
        this.items = items;
    }

    private int countItems(){
        return items.size();
    }

    private Long countSum(){
       return items.values().stream().reduce(0L, Long::sum);
    }

    @Override
    public String toString() {
        return "items: " + countItems() + " sum: " + countSum();
    }
}
