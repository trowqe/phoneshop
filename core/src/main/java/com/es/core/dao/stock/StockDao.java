package com.es.core.dao.stock;

import com.es.core.model.phone.Stock;

public interface StockDao {
    Stock getStock(Long phoneId);
}
