package com.es.core.service;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneDao phoneDao;

    @Override
    public List<Phone> findAll(int offset, int limit) {
        return phoneDao.findAll(0, 3000);
    }

    @Override
    public List<Phone> sort(SortField sortField, SortType sortType) {
        List<Phone> phones = null;
        switch (sortField){
            case BRAND: { phones =  phoneDao.sortByBrand(sortType); break;}
            case MODEL: { phones =  phoneDao.sortByModel(sortType); break;}
            case PRICE: { phones =  phoneDao.sortByPrise(sortType); break;}
            case DISPLAY_SIZE:{ phones =  phoneDao.sortByDisplaySize(sortType); break;}
        }
        return phones;
    }

}
