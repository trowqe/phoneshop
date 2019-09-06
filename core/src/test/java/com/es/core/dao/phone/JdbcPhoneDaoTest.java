package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/applicationContext-coreTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JdbcPhoneDaoTest {

    @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void countEmptyTable() {
        Long count = jdbcPhoneDao.countAllPhones("");
        assertEquals(Long.valueOf(0), count);
    }

    @Test
    public void countThreeOfThree() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        jdbcTemplate.update(sql, id2, 5, 5);

        Phone phone3 = new Phone();
        phone3.setBrand("motorola3");
        phone3.setModel("x style3");
        phone3.setPrice(BigDecimal.valueOf(10));
        Long id3 = jdbcPhoneDao.save(phone3);
        jdbcTemplate.update(sql, id3, 5, 5);

        Long count = jdbcPhoneDao.countAllPhones("");
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void countTwoOfFour() {
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";

        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(BigDecimal.valueOf(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("motorola2");
        phone2.setModel("x style2");
        phone2.setPrice(BigDecimal.valueOf(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        jdbcTemplate.update(sql, id2, 5, 5);

        Phone phoneWithInvalidStocksLevel = new Phone();
        phoneWithInvalidStocksLevel.setBrand("motorola3");
        phoneWithInvalidStocksLevel.setModel("x style3");
        phoneWithInvalidStocksLevel.setPrice(BigDecimal.valueOf(10));
        Long id3 = jdbcPhoneDao.save(phoneWithInvalidStocksLevel);
        jdbcTemplate.update(sql, id3, 0, 0);

        Phone phoneWithNullPrise = new Phone();
        phoneWithNullPrise.setBrand("motorola4");
        phoneWithNullPrise.setModel("x style4");
        phoneWithNullPrise.setPrice(BigDecimal.valueOf(0));
        Long id4 = jdbcPhoneDao.save(phoneWithNullPrise);
        jdbcTemplate.update(sql, id4, 5, 5);

        Long count = jdbcPhoneDao.countAllPhones("");
        assertEquals(Long.valueOf(2), count);
    }

    @Test
    public void save() {
        Phone phone = new Phone();
        phone.setBrand("motorola");
        phone.setModel("x style");
        phone.setPrice(new BigDecimal(10));
        Long id = jdbcPhoneDao.save(phone);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, 5, 5);
        Optional<Phone> testPhone = jdbcPhoneDao.get(id);
        assertTrue(testPhone.isPresent());
    }

    @Test
    public void getExisting() {
        Phone phone = new Phone();
        phone.setBrand("motorola");
        phone.setModel("x style");
        phone.setPrice(new BigDecimal(10));
        Long id = jdbcPhoneDao.save(phone);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, 5, 5);
        Optional<Phone> testPhone = jdbcPhoneDao.get(id);
        assertEquals(phone, testPhone.get());
    }

    @Test
    public void getNotExisting() {
        Optional<Phone> testPhone = jdbcPhoneDao.get(-2000L);
        assertEquals(Optional.empty(), testPhone);
    }

    @Test
    public void findAll() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("apple");
        phone2.setModel("10");
        phone2.setPrice(new BigDecimal(10));
        Long id2 = jdbcPhoneDao.save(phone2);
        String sql2 = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql2, id2, 5, 5);

        Optional<List<Phone>> twoPhoneList = jdbcPhoneDao.findAll(0, 3, "", SortField.PHONE_ID, SortType.ASC);
        assertEquals(2, twoPhoneList.get().size());
    }

    @Test
    public void sortByFieldPrice() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("apple");
        phone2.setModel("10");
        phone2.setPrice(new BigDecimal(5));
        Long id2 = jdbcPhoneDao.save(phone2);
        String sql2 = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql2, id2, 5, 5);

        Optional<List<Phone>> list = jdbcPhoneDao.findAll(0, 2, "", SortField.PRICE, SortType.DESC);

        assertEquals(-1, (list.get().get(0).getPrice().compareTo(list.get().get(1).getPrice())));
    }


    @Test
    public void userSearchByModel() {
        Phone phone1 = new Phone();
        phone1.setBrand("motorola");
        phone1.setModel("motorola x style");
        phone1.setPrice(new BigDecimal(10));
        Long id1 = jdbcPhoneDao.save(phone1);
        String sql = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id1, 5, 5);

        Phone phone2 = new Phone();
        phone2.setBrand("moto");
        phone2.setModel("moto 10");
        phone2.setPrice(new BigDecimal(5));
        Long id2 = jdbcPhoneDao.save(phone2);
        String sql2 = "INSERT INTO stocks (phoneId, stock, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql2, id2, 5, 5);

        Optional<List<Phone>> list = jdbcPhoneDao.findAll(0, 10, "moto", SortField.PHONE_ID, SortType.ASC);
        assertEquals(2, list.get().size());
    }


    @Test
    public void userBadSearchByModel() {
        Optional<List<Phone>> list = jdbcPhoneDao.findAll(0, 10, "lalallallalalala", SortField.PHONE_ID, SortType.ASC);
        assertTrue(list.get().isEmpty());
    }

    @Test
    public void countTotalPrice() {
        Phone phone1 = new Phone();
        phone1.setBrand("1");
        phone1.setModel("1");
        phone1.setPrice(BigDecimal.valueOf(10.0));
        Long id1 = jdbcPhoneDao.save(phone1);
        Phone phone2 = new Phone();
        phone2.setBrand("2");
        phone2.setModel("2");
        phone2.setPrice(BigDecimal.valueOf(20.0));
        Long id2 = jdbcPhoneDao.save(phone2);
        Phone phone3 = new Phone();
        phone3.setBrand("3");
        phone3.setModel("3");
        phone3.setPrice(BigDecimal.valueOf(30.0));
        Long id3 = jdbcPhoneDao.save(phone3);

        List<Long> ids = new ArrayList();
        ids.add(id1);
        ids.add(id2);
        ids.add(id3);

        Map<Long, BigDecimal> map = new HashMap();
        map.put(id1, phone1.getPrice());
        map.put(id2, phone2.getPrice());
        map.put(id3, phone3.getPrice());

        assertEquals(map, jdbcPhoneDao.countTotalPriceByPhoneIds(ids).get());
    }

    @Test
    public void countTotalPriceInvalidIds() {
        List<Long> ids = new ArrayList();
        ids.add(12L);
        ids.add(18L);
        ids.add(20L);

        assertEquals(Optional.empty(), jdbcPhoneDao.countTotalPriceByPhoneIds(ids));
    }

}
