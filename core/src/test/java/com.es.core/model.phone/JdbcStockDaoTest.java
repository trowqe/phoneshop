package com.es.core.model.phone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/testApplicationContext.xml")
public class JdbcStockDaoTest {

    @Autowired
    private JdbcStockDao jdbcStockDao;

    @Autowired
    private JdbcTemplate jdbcTemplateTest;

    @Test
    public void getStockByExistingPhoneId() {
        Stock stock = jdbcStockDao.getStock(1001L);
        Assert.assertNotNull(stock);
    }

    @Test
    public void getStockByNotExistingPhoneId() {
        Stock stock = jdbcStockDao.getStock(-1001L);
        Assert.assertNull(stock);
    }
}
