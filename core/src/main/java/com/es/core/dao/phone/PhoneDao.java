package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);

    Long save(Phone phone);

    Long countAllPhones(String search);

    List findAll(int offset, int limit, String searchString, SortField sortField, SortType sortType);

}
