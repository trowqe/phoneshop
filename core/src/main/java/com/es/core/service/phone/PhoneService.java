package com.es.core.service.phone;

import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PhoneService {
    List<Phone> findAll(int offset, int limit, String userSearch, SortField sortField, SortType sortType);

    Page<Phone> paginatePhoneList(Pageable pageable, List<Phone> phones);
}
