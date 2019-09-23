package com.es.core.service.cart;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    private Cart cart;

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        cart.addItem(phoneId, quantity);
    }

    @Override
    public void update(Map<Long, Long> newMap) {
        Map<Long, Long> currMap = cart.getItems();
        currMap.keySet().removeAll(newMap.keySet());
        currMap.putAll(newMap);
        currMap.entrySet().removeIf(v->v.getValue().equals(Long.valueOf(0)));
    }

    @Override
    public void remove(Long phoneId) {
        cart.removeItem(phoneId);
    }

    @Override
    public Long countTotalItem() {
        return cart.getItems().values().stream().mapToLong(i -> i).sum();
    }

    @Override
    public BigDecimal countTotalSum() {

        BigDecimal sum = BigDecimal.ZERO;

        Map<Long, Long> items = cart.getItems();
        for (Map.Entry<Long, Long> entry : items.entrySet()) {
            Long id = entry.getKey();
            Long quantity = entry.getValue();
            BigDecimal price = phoneDao.get(id).get().getPrice();
            sum = sum.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return sum;
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}

