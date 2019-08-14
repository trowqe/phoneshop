package com.es.core.service;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.SortType;


import java.util.List;

public interface PhoneService {
    List<Phone> findAll(int offset, int limit);

    List<Phone> sort(SortField sortField, SortType sortType);
}
