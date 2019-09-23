package com.es.core.model.order;

import com.es.core.model.phone.Phone;

import javax.validation.constraints.Min;


public class OrderItem {
    private Phone phone;
    private Order order;
    @Min(1)
    private Long quantity;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(final Phone phone) {
        this.phone = phone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                ", phone=" + phone +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
