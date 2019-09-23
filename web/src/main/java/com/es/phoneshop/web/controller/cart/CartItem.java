package com.es.phoneshop.web.controller.cart;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItem {
    private Long itemId;

    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private Long itemQuantity;

    public CartItem(Long itemId, Long itemQuantity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    public CartItem() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
