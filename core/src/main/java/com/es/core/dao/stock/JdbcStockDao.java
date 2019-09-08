package com.es.core.dao.stock;

import com.es.core.model.phone.Stock;
import com.es.core.service.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcStockDao implements StockDao {

    private final String SQL_UPDATE_STOCK_BY_ID = "update stocks set stock = :stock, reserved = :reserved where phoneId = :phoneId";
    private final String SQL_SELECT_STOCK_BY_ID = "select * from stocks where phoneId = :phoneId";
    private final String SQL_INSERT_STOCK = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (:phoneId, :stock, :reserved)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PhoneService phoneService;

    @Override
    public Optional<Stock> get(Long key) {
        Map<String, Number> paramMap = new HashMap<>();
        paramMap.put("phoneId", key);
        try {
            Stock stock = namedParameterJdbcTemplate.
                    queryForObject(SQL_SELECT_STOCK_BY_ID, paramMap, new BeanPropertyRowMapper<>(Stock.class));
            stock.setPhone(phoneService.get(key));
            return Optional.of(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Stock stock) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("stock", stock.getStock());
        mapSqlParameterSource.addValue("reserved", stock.getReserved());
        mapSqlParameterSource.addValue("phoneId", stock.getPhone().getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_STOCK_BY_ID, mapSqlParameterSource);
    }

    @Override
    public void save(Stock stock) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("stock", stock.getStock());
        mapSqlParameterSource.addValue("reserved", stock.getReserved());
        mapSqlParameterSource.addValue("phoneId", stock.getPhone().getId());
        namedParameterJdbcTemplate.update(SQL_INSERT_STOCK, mapSqlParameterSource);
    }
}
