package com.es.core.dao.stock;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcStockDao implements StockDao {

    private final JdbcTemplate jdbcTemplate;
    private final PhoneDao phoneDao;

    @Autowired
    public JdbcStockDao(JdbcTemplate jdbcTemplate, PhoneDao phoneDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.phoneDao = phoneDao;
    }

    @Override
    public Stock getStock(Long phoneId) {
        try {
            return jdbcTemplate.queryForObject("select * from stocks where phoneId = ?", new Object[]{phoneId},
                    new BeanPropertyRowMapper<>(Stock.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
