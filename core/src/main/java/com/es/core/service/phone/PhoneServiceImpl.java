package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public List<Phone> findAll(int offset, int limit, String userInput, SortField sortField, SortType sortType) {
        Optional<List<Phone>> phonesOptionalList = phoneDao.findAll(offset, limit, userInput, sortField, sortType);
        if(phonesOptionalList.isPresent()){
            return phonesOptionalList.get();
        }else throw new ItemNotFoundException("find all return empty list");
    }

}


