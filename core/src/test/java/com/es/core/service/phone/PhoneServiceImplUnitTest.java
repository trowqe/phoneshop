package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.phone.Phone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class PhoneServiceImplUnitTest {

    @InjectMocks
    private PhoneServiceImpl phoneService = new PhoneServiceImpl();

    @Mock
    private PhoneDao phoneDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(phoneService, "limit", 10);
    }

    @Test
    public void get() {
        when(phoneDao.get(any(Long.class))).thenReturn(Optional.of(new Phone()));
        assertNotNull(phoneService.get(1L));
    }

    @Test(expected = ItemNotFoundException.class)
    public void getWithNotExcitingId() {
        when(phoneDao.get(any(Long.class))).thenReturn(Optional.empty());
        phoneService.get(1L);
    }

    @Test
    public void paginatedPhoneList() {
        List<Phone> phones = new ArrayList<>();
        when(phoneDao.findAll(any(Integer.class), any(Integer.class), any(String.class), any(SortField.class), any(SortType.class)))
                .thenReturn(phones);
        assertNotNull(phoneService.paginatedPhoneList(3, "sdf", SortField.PHONE_ID, SortType.ASC));
    }

    @Test
    public void countTotalEvenPages() {
        when(phoneDao.countAllPhones(any(String.class))).thenReturn(100L);
        assertEquals(Long.valueOf(10), phoneService.countTotalPages("f"));

    }

    @Test
    public void countTotalOddPages() {
        when(phoneDao.countAllPhones(any(String.class))).thenReturn(101L);
        assertEquals(Long.valueOf(11), phoneService.countTotalPages("f"));
    }

}
