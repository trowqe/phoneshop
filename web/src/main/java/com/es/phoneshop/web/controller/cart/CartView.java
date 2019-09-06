package com.es.phoneshop.web.controller.cart;

import com.es.core.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartView {

    @Autowired
    private CartService cartService;

    private Long totalItems = 0L;

    private BigDecimal totalSum = BigDecimal.valueOf(0);

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
