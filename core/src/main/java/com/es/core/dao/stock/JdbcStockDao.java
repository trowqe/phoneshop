package com.es.core.dao.stock;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JdbcStockDao implements StockDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PhoneDao phoneDao;

    @Autowired
    public JdbcStockDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, PhoneDao phoneDao) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.phoneDao = phoneDao;
    }

    @Override
    public Stock getStock(Long phoneId) {
        try {
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("stocks.phoneId", phoneId);
            return namedParameterJdbcTemplate.queryForObject("select * from stocks where phoneId = ?", paramMap,
                    new BeanPropertyRowMapper<>(Stock.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
