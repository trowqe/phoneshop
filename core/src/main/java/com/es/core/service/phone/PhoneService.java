package com.es.core.service.phone;

import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;


import java.util.List;

public interface PhoneService {
    Phone get(Long id);

    List<Phone> findAll(int offset, String userSearch, SortField sortField, SortType sortType);

    List<Phone> paginatedPhoneList(int pageSize, int currentPage, String userInput, SortField sortField, SortType sortType);
}
