package com.es.core.model.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
