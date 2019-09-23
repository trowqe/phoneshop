package com.es.core.service.stock;

import com.es.core.dao.stock.StockDao;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OutOfStockException;
import com.es.core.model.phone.Phone;
import com.es.core.model.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockDao stockDao;

    @Override
    public void checkStock(OrderItem orderItem) throws OutOfStockException {
        Phone phone = orderItem.getPhone();
        Stock stock = stockDao.get(phone.getId()).orElseThrow(OutOfStockException::new);
        if(orderItem.getQuantity()>stock.getStock()){
            throw new OutOfStockException();
        }
    }

    @Override
    public void updateStock(OrderItem orderItem) throws OutOfStockException {
        Stock  stock = stockDao.get(orderItem.getPhone().getId()).orElseThrow(OutOfStockException::new);
        stock.setStock((int) (stock.getStock() - orderItem.getQuantity()));
        stockDao.update(stock);
    }
}
