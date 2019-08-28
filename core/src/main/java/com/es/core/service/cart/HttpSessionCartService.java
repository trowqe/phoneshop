package com.es.core.service.cart;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    private Cart cart;

    @Autowired
    PhoneDao phoneDao;

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

    @Override
    public Long countTotalItem() {
        return cart.getItems().values().stream().mapToLong(i -> i).sum();
    }

    @Override
    public BigDecimal countTotalSum() {
        Map<Long, Long> items = cart.getItems();
        List<Long> ids =  items.keySet().stream().collect(Collectors.toList());
        Optional<Map<Long, BigDecimal>> pricesByIdOptional = (phoneDao.countTotalPriceByPhoneIds(ids));
        if (!pricesByIdOptional.isPresent()) return BigDecimal.ZERO;
        Map<Long, BigDecimal> pricesById = pricesByIdOptional.get();
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Long, Long> entry : items.entrySet()) {
            Long id = entry.getKey();
            Long quantity = entry.getValue();
            BigDecimal curSum = pricesById.get(Long.valueOf(id)).multiply(BigDecimal.valueOf(quantity));
            sum = sum.add(curSum);
        }
        return sum;
    }
}

