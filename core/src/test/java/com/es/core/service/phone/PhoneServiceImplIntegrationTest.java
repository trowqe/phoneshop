package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.phone.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PhoneServiceImplIntegrationTest {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneDao phoneDao;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void get() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = phoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);
        System.out.println(phoneService.get(id1));
        assertEquals(phone1, phoneService.get(id1));
    }

    @Test(expected = ItemNotFoundException.class)
    public void getWithNotExcitingId() {
        phoneService.get(1L);
    }

    @Test
    public void paginatedPhoneList() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = phoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("z style");
        phone2.setPrice(new BigDecimal(5));
        Long id2 = phoneDao.save(phone2);
        String sql2 = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql2, id2, 5, 5);

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        assertEquals(phones, phoneService.paginatedPhoneList(0, "style", SortField.PRICE, SortType.ASC));
    }

    @Test
    public void countTotalPages() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = phoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);

        assertEquals(Long.valueOf(1), phoneService.countTotalPages(""));
    }

}
