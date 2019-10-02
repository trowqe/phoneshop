package com.es.core.service.stock;

import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OutOfStockException;

public interface StockService {
    void checkStock(OrderItem orderItem) throws OutOfStockException;
    void updateStock(OrderItem orderItem) throws OutOfStockException;
}
