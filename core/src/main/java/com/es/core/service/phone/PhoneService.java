package com.es.core.service.phone;

import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;


import java.util.List;

public interface PhoneService {
    List<Phone> findAll(int offset, int limit);

    List<Phone> sort(SortField sortField, SortType sortType);
}
