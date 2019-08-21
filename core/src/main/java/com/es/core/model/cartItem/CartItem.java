package com.es.core.model.cartItem;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItem {
    private Long itemId;

    @NotNull
    @Min(1)
    @Max(5)
    private Long itemQuantity;

    public CartItem(Long itemId, @NotNull @Min(1) @Max(5) Long itemQuantity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
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
}
