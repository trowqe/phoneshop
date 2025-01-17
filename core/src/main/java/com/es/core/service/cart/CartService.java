package com.es.core.service.cart;


import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartService {

    void addPhone(Long phoneId, Long quantity);

    /**
     * @param items key: {@link com.es.core.model.phone.Phone#id}
     *              value: quantity
     */
    void update(Map<Long, Long> items);

    void remove(Long phoneId);

    Long countTotalItem();

    BigDecimal countTotalSum();

    Cart getCart();

    List<Phone> getPhonesInCart();

    void clearCart();

}
