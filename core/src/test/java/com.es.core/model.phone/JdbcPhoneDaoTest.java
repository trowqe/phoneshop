package com.es.core.model.phone;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/testApplicationContext.xml")
public class JdbcPhoneDaoTest {

    @Autowired
    private JdbcPhoneDao jdbcPhoneDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetExistingProduct() {
        Optional<Phone> phoneFromDB = jdbcPhoneDao.get(1000L);
        assertNotNull(phoneFromDB);
    }

    @Test
    public void testGetNonExistingProduct() {
        Optional<Phone> notExistingPhoneFromDB = jdbcPhoneDao.get(9999L);
        assertFalse(notExistingPhoneFromDB.isPresent());
    }

    @Test
    public void testUserSearchWithNoResult() {
        List<Phone> phones = jdbcPhoneDao.userSearchByModel("kjfhx jhf g6");
        assertEquals(0, phones.size());
    }

    @Test
    public void testUserSearchWithExistModels() {
        List<Phone> phones = jdbcPhoneDao.userSearchByModel("lenovo a 3".toUpperCase());
        assertEquals(2, phones.size());
    }

 /*   @Test
    public void sortByBrand() {
        List<Phone> actual = jdbcPhoneDao.sortByBrand();
        List<Phone> sorted = jdbcPhoneDao.findAll(0, 30);
        Collections.sort(sorted, (p1, p2) -> p1.getBrand().compareToIgnoreCase(p2.getBrand()));
        assertEquals(actual, sorted);
    }*/

}
