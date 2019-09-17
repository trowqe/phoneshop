package com.es.core.service.cart;

import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    private Cart cart;

    @Autowired
    private PhoneService phoneService;

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        cart.addItem(phoneId, quantity);
    }

    @Override
    public void update(Map<Long, Long> newMap) {
        Map<Long, Long> currMap = cart.getItems();
        currMap.putAll(newMap);
    }

    @Override
    public void remove(Long phoneId) {
        cart.removeItem(phoneId);
    }

    @Override
    public Long countTotalItem() {
        return cart.getItems()
                .values()
                .stream()
                .mapToLong(i -> i)
                .sum();
    }

    @Override
    public BigDecimal countTotalSum() {

        BigDecimal sum = BigDecimal.ZERO;

        Map<Long, Long> items = cart.getItems();
        for (Map.Entry<Long, Long> entry : items.entrySet()) {
            Long id = entry.getKey();
            Long quantity = entry.getValue();
            BigDecimal price = phoneService.get(id).getPrice();
            sum = sum.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return sum;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public List<Phone> getPhonesInCart() {

        List<Phone> phones = new ArrayList<>();
        cart.getItems().
                forEach((id, quantity) -> {
                    phones.add(phoneService.get(id));
                });
        return phones;

    }
}

