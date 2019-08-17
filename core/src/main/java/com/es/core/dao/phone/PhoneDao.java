package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    Long save(Phone phone);
    List<Phone> findAll(int offset, int limit);

    List<Phone> sortByField(SortField sortField, SortType sortType, int limit);

    List<Phone> userSearchByModel(String searchString, int limit);
}
