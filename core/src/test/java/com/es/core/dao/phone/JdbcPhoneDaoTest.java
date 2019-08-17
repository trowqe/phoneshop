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
import java.util.List;
import java.util.Optional;

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
        System.out.println(phone);
        System.out.println(testPhone.get());
        assertEquals(phone, testPhone.get());
    }

    @Test
    public void getNotExisting(){
        Optional<Phone> testPhone = jdbcPhoneDao.get(-2000L);
        assertEquals(Optional.empty(), testPhone);
    }

    @Test
    public void findAll(){
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

        List<Phone> twoPhoneList = jdbcPhoneDao.findAll(0, 3);
        assertEquals(2, twoPhoneList.size());
    }

    @Test
    public void sortByFieldPrice(){
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

        List<Phone> list = jdbcPhoneDao.sortByField(SortField.PRICE, SortType.DESC, 2);

        assertEquals(-1, (list.get(0).getPrice().compareTo( list.get(1).getPrice())));
    }

    @Test
    public void userSearchByModel(){
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

        List<Phone> list = jdbcPhoneDao.userSearchByModel("moto", 2);
        assertEquals(2, list.size());
    }

    @Test
    public void userBadSearchByModel(){
        List<Phone> list = jdbcPhoneDao.userSearchByModel("sdjfsbdfo", 2);
        assertEquals(0, list.size());
    }
}
