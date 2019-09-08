package com.es.core.dao.stock;

import com.es.core.dao.phone.JdbcPhoneDao;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JdbcStockDaoTest {


    @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcStockDao jdbcStockDao;

    @Test
    public void getNotExisting() {
        assertEquals(Optional.empty(), jdbcStockDao.get(Long.valueOf(0)));
    }

    @Test
    public void update() {
        Phone phone = createPhone();
        Long phoneId = jdbcPhoneDao.save(phone);
        phone.setId(phoneId);
        Stock stock = new Stock();
        stock.setStock(3);
        stock.setPhone(phone);
        stock.setReserved(3);
        jdbcStockDao.save(stock);
        assertEquals(jdbcStockDao.get(phoneId).get().getPhone().getId(), stock.getPhone().getId());
        assertEquals(jdbcStockDao.get(phoneId).get().getReserved(), stock.getReserved());
        assertEquals(jdbcStockDao.get(phoneId).get().getStock(), stock.getStock());
        stock.setStock(5);
        stock.setReserved(5);
        jdbcStockDao.update(stock);
        assertEquals(jdbcStockDao.get(phoneId).get().getReserved(), stock.getReserved());
        assertEquals(jdbcStockDao.get(phoneId).get().getStock(), stock.getStock());
    }

    @Test
    public void saveAndGetExisting() {
        Phone phone = createPhone();
        Long phoneId = jdbcPhoneDao.save(phone);
        phone.setId(phoneId);
        Stock stock = new Stock();
        stock.setStock(5);
        stock.setPhone(phone);
        stock.setReserved(3);
        jdbcStockDao.save(stock);
        assertEquals(jdbcStockDao.get(phoneId).get().getPhone().getId(), stock.getPhone().getId());
        assertEquals(jdbcStockDao.get(phoneId).get().getReserved(), stock.getReserved());
        assertEquals(jdbcStockDao.get(phoneId).get().getStock(), stock.getStock());
    }

    private Phone createPhone() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        return phone1;
    }

}
