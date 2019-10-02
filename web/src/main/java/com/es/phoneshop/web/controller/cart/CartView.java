package com.es.phoneshop.web.controller.cart;

import com.es.core.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;

@Component
@RequestScope
public class CartView {

    private Long totalItems;

    private BigDecimal totalSum;

    @Autowired
    public CartView(CartService cartService) {
        this.totalItems = cartService.countTotalItem();
        this.totalSum = cartService.countTotalSum();
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "CartView{" +
                "totalItems=" + totalItems +
                ", totalSum=" + totalSum +
                '}';
    }
}
