package com.es.core.model.cartItem;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

public class CartItem {
    private Long itemId;

   // @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="msg")
    @Min(value = 1, message = "error quantity can be zero")
  //  @NotBlank(message="eeeeeeee")
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
}
