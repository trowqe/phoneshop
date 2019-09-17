package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.phone.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    @Value("${findAll.limit}")
    private Integer limit;

    @Override
    public Phone get(Long id) {
        return phoneDao.get(id).orElseThrow(()-> new ItemNotFoundException("phone dao can't find phone with id: " + id));
    }

    @Override
    public List<Phone> paginatedPhoneList(int currentPage, String userInput, SortField sortField, SortType sortType) {
        int startItem = currentPage * limit;
        return phoneDao.findAll(startItem, limit, userInput, sortField, sortType);
    }

    @Override
    public Long countTotalPages(String search) {
        return phoneDao.countAllPhones(search) % limit > 0 ? phoneDao.countAllPhones(search) / limit + 1: phoneDao.countAllPhones(search) / limit ;
    }

}


