package com.es.core.service.phone;

import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneDao phoneDao;

    @Override
    public List<Phone> findAll(int offset, int limit, String userInput, SortField sortField, SortType sortType) {
        return phoneDao.findAll(0, 1000, userInput, sortField, sortType);
    }

}


